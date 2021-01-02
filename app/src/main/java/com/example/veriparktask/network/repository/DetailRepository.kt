package com.example.veriparktask.network.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.veriparktask.constant.Constants
import com.example.veriparktask.network.model.request.Id
import com.example.veriparktask.network.model.response.StockDetail
import com.example.veriparktask.network.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class DetailRepository(context: Context, id: Id) {
    private val mutableTags = MutableLiveData<StockDetail>()
    val stockDetail: LiveData<StockDetail>
        get() = mutableTags

    var mPrefs: SharedPreferences =
        context.getSharedPreferences(Constants.PREFS_FILENAME, Context.MODE_PRIVATE)

    init {
        RetrofitClient.apiService.getStockDetail(
            mPrefs.getString("AUTHORIZATION", "").toString(),
            id
        ).enqueue(object : Callback<StockDetail> {
            override fun onFailure(call: Call<StockDetail>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<StockDetail>,
                response: Response<StockDetail>
            ) {
                mutableTags.value = response.body()
                Log.v("Tag:", response.body()?.count.toString() + " idcount")
            }
        })
    }
}