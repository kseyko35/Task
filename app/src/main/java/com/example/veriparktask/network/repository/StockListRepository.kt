package com.example.veriparktask.network.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.veriparktask.constant.Constants
import com.example.veriparktask.network.model.request.Period
import com.example.veriparktask.network.model.response.StockList
import com.example.veriparktask.network.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class StockListRepository(context: Context, period: Period) {
    private val mutableTags = MutableLiveData<StockList>()

    val stockList: LiveData<StockList>
        get() = mutableTags

    var mPrefs: SharedPreferences =
        context.getSharedPreferences(Constants.PREFS_FILENAME, Context.MODE_PRIVATE)

    init {
        Log.v("Tag:", period.period + " period")
        RetrofitClient.apiService.getStockList(
            mPrefs.getString("AUTHORIZATION", "").toString(),
            period
        ).enqueue(object : Callback<StockList> {
            override fun onFailure(call: Call<StockList>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<StockList>,
                response: Response<StockList>
            ) {
                mutableTags.value = response.body()
            }
        })
    }
}