package com.example.veriparktask.data.model.response



data class Stocks(

    val id: Int,
    val isDown: Boolean,
    val isUp: Boolean,
    val bid: Float,
    val difference: Float,
    val offer: Float,
    val price: Float,
    val volume: Float,
    val symbol: String,
    var encryptData: String
)