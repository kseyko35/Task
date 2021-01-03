package com.example.veriparktask.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.veriparktask.util.Constants
import com.example.veriparktask.data.model.request.Period
import com.example.veriparktask.data.model.response.StockList
import com.example.veriparktask.data.repository.StockListRepository

class Volume30ViewModel(application: Application) : BaseViewModel(application) {
    private val period: Period = Period(baseEncrypt(Constants.PERIOD_VOLUME30))
    internal val stockList: LiveData<StockList>?
    private val stockListRepository: StockListRepository = StockListRepository(application, period)

    init {
        stockList = stockListRepository.stockList
    }
}