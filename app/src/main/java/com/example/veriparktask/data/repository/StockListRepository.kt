package com.example.veriparktask.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.veriparktask.util.Constants
import com.example.veriparktask.data.model.request.Period
import com.example.veriparktask.data.model.response.StockList
import com.example.veriparktask.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class StockListRepository(context: Context, period: Period) {
    private val mutableTags = MutableLiveData<StockList>()

    val stockList: LiveData<StockList>
        get() = mutableTags

    private var mPrefs: SharedPreferences =
        context.getSharedPreferences(Constants.PREFS_FILENAME, Context.MODE_PRIVATE)

    init {
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