package com.example.veriparktask.network.model.response


data class StockList(
    val stocks: List<Stocks>,
    val status: Status
)