package com.example.veriparktask.network.action

import android.app.Application
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.provider.Settings.Secure.getString
import android.util.Log
import com.example.veriparktask.constant.Constants
import com.example.veriparktask.network.model.request.HandshakeStart
import com.example.veriparktask.network.model.response.Handshake
import com.example.veriparktask.network.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class AuthAction(application: Application) {
//    private val mutableTags = MutableLiveData<Handshake>()
//    val handshake: LiveData<Handshake>
//        get() = mutableTags

    val prefences = application.getSharedPreferences(
        Constants.PREFS_FILENAME,
        Context.MODE_PRIVATE
    )


    init {
        RetrofitClient.apiService.handshakeProcess(
            HandshakeStart(
                deviceId = getString(application.contentResolver, Settings.Secure.ANDROID_ID),
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
//                mutableTags.value = response.body()
                prefences.edit().putString(Constants.AESKEY, response.body()?.aesKey).apply()
                prefences.edit().putString(Constants.AESIVKEY, response.body()?.aesIV).apply()
                prefences.edit().putString(Constants.AUTHORIZATION, response.body()?.authorization)
                    .apply()
                Log.v("Tag:", response.body()?.authorization + " authorization")

            }
        })
    }
}