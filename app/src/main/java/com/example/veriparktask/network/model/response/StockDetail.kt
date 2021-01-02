package com.example.veriparktask.network.model.response



data class StockDetail(
    val isDown: Boolean,
    val isUp: Boolean,
    val bid: Float,
    val channge: Float,
    val count: Int,
    val difference: Float,
    val offer: Float,
    val highest: Float,
    val lowest: Float,
    val maximum: Float,
    val minimum: Float,
    val price: Float,
    val volume: Float,
    val symbol: String,
    val graphicData: List<GraphicData>,
    val status: Status
)