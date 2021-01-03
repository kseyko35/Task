package com.example.veriparktask.network

import com.example.veriparktask.util.Constants.BASE_URL
import com.example.veriparktask.network.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


abstract class RetrofitClient {
    companion object {

        val apiService: ApiService = getRetrofit()
            .create(ApiService::class.java)

        @Volatile
        private var INSTANCE: Retrofit? = null

        private fun getRetrofit(): Retrofit { 
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}