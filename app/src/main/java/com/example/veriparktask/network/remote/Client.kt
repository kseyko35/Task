package com.example.veriparktask.network.remote

import com.example.veriparktask.constant.Constants.BASE_URL
import com.example.veriparktask.network.remote.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


abstract class RetrofitClient {
    companion object {

        val apiService = getRetrofit()
            .create(ApiService::class.java)

        @Volatile // Farkli threadlerin ayni anda degiskene degisiklik yapmasina engel olmak icin kullanildi.
        private var INSTANCE: Retrofit? = null

        fun getRetrofit(): Retrofit { // Singleton yapisi kullanilarak ayni class bir daha olusturulmamasi icin kullanildi
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create()) // Cast isleminin hangi kutuphane ile yapilacagini gosterdik.
                    //Burada 4 kutuphane kullanilabilir. GSON,MOSHI ve JACKSON gibi
                    .baseUrl(BASE_URL)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}