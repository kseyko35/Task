package com.example.veriparktask.data.model.response



data class Handshake(

    val aesKey: String,
    val aesIV: String,
    val authorization: String,
    val lifeTime: String,
    val status: Status
)