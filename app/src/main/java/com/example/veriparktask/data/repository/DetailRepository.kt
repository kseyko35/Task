package com.example.veriparktask.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.veriparktask.util.Constants
import com.example.veriparktask.data.model.request.Id
import com.example.veriparktask.data.model.response.StockDetail
import com.example.veriparktask.network.RetrofitClient
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
            }
        })
    }
}