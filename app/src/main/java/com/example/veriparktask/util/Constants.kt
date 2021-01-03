package com.example.veriparktask.util



object Constants {
    const val BASE_URL: String = "https://mobilechallenge.veripark.com/api/"
    const val PREFS_FILENAME: String = "Auth"

    val PERIOD_ALL: ByteArray = "all".toByteArray()
    val PERIOD_INCREASING: ByteArray = "increasing".toByteArray()
    val PERIOD_DECREASING: ByteArray = "decreasing".toByteArray()
    val PERIOD_VOLUME30: ByteArray = "volume30".toByteArray()
    val PERIOD_VOLUME50: ByteArray = "volume50".toByteArray()
    val PERIOD_VOLUME100: ByteArray = "volume100".toByteArray()

    const val ALGORITHM: String = "AES"
    const val AESIVKEY: String = "AESIVKEY"
    const val AESKEY: String = "AESKEY"
    const val AUTHORIZATION = "AUTHORIZATION"
}