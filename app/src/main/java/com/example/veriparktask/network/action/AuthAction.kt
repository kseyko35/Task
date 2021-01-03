package com.example.veriparktask.network.action

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.provider.Settings
import android.provider.Settings.Secure.getString
import com.example.veriparktask.util.Constants
import com.example.veriparktask.data.model.request.HandshakeStart
import com.example.veriparktask.data.model.response.Handshake
import com.example.veriparktask.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class AuthAction(context: Context) {


    val preferences: SharedPreferences = context.getSharedPreferences(
        Constants.PREFS_FILENAME,
        Context.MODE_PRIVATE
    )


    init {
        RetrofitClient.apiService.handshakeProcess(
            HandshakeStart(
                deviceId = getString(context.contentResolver, Settings.Secure.ANDROID_ID),
                systemVersion = Build.VERSION.RELEASE,
                platformName = "Android",
                deviceModel = Build.MODEL,
                manifacturer = Build.MANUFACTURER
            )
        ).enqueue(object : Callback<Handshake> {
            override fun onFailure(call: Call<Handshake>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<Handshake>,
                response: Response<Handshake>
            ) {
                preferences.edit().putString(Constants.AESKEY, response.body()?.aesKey).apply()
                preferences.edit().putString(Constants.AESIVKEY, response.body()?.aesIV).apply()
                preferences.edit().putString(Constants.AUTHORIZATION, response.body()?.authorization)
                    .apply()

            }
        })
    }
}