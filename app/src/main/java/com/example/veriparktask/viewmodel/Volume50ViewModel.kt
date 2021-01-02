package com.example.veriparktask.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.veriparktask.constant.Constants
import com.example.veriparktask.network.model.request.Period
import com.example.veriparktask.network.model.response.StockList
import com.example.veriparktask.network.repository.StockListRepository

class Volume50ViewModel(application: Application) : BaseViewModel(application) {

    private val period: Period = Period(baseEncrypt(Constants.PERIOD_VOLUME50))
    internal val stockList: LiveData<StockList>?
    private val stockListRepository: StockListRepository = StockListRepository(application, period)

    init {
        stockList = stockListRepository.stockList
    }

}