package com.example.veriparktask.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.veriparktask.data.model.request.Id
import com.example.veriparktask.data.model.response.StockDetail
import com.example.veriparktask.data.repository.DetailRepository

class DetailViewModel(application: Application, id: Int?) : BaseViewModel(application) {
    private val idDetail: Id = Id(baseEncrypt(id.toString().toByteArray()))

    internal val stockDetail: LiveData<StockDetail>
    private val stockDetailRepository: DetailRepository = DetailRepository(application, idDetail)

    init {
        stockDetail = stockDetailRepository.stockDetail
    }


}