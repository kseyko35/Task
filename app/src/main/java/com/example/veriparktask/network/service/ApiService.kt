package com.example.veriparktask.network.service


import com.example.veriparktask.data.model.request.HandshakeStart
import com.example.veriparktask.data.model.request.Id
import com.example.veriparktask.data.model.request.Period
import com.example.veriparktask.data.model.response.Handshake
import com.example.veriparktask.data.model.response.StockDetail
import com.example.veriparktask.data.model.response.StockList
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiService {
    @POST("handshake/start")
    fun handshakeProcess(@Body handshake: HandshakeStart): Call<Handshake>

    @POST("stocks/list")
    fun getStockList(
        @Header("X-VP-Authorization") authKey: String,
        @Body period: Period
    ): Call<StockList>

    @POST("stocks/detail")
    fun getStockDetail(
        @Header("X-VP-Authorization") authKey: String,
        @Body id: Id
    ): Call<StockDetail>
}