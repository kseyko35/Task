package com.example.veriparktask.data.model.request



data class HandshakeStart(
    val deviceId: String,
    val systemVersion: String,
    val platformName: String,
    val deviceModel: String,
    val manifacturer: String
)