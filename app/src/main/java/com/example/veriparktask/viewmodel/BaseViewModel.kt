package com.example.veriparktask.viewmodel

import android.app.Application
import android.content.Context
import android.util.Base64
import androidx.lifecycle.AndroidViewModel
import com.example.veriparktask.constant.Constants
import com.example.veriparktask.functions.Encrypt
import javax.crypto.spec.SecretKeySpec


open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val application1: Application = application

    fun baseEncrypt(period: ByteArray): String {

        val prefences =
            application1.getSharedPreferences(Constants.PREFS_FILENAME, Context.MODE_PRIVATE)

        val specDecode = Base64.decode(prefences?.getString(Constants.AESKEY, ""), Base64.NO_WRAP)
        val IVdecode = Base64.decode(prefences?.getString(Constants.AESIVKEY, ""), Base64.NO_WRAP)
        val keySpec = SecretKeySpec(specDecode, Constants.ALGORITHM)


        val result: ByteArray = Encrypt.encrypt(period, keySpec, IVdecode)
        val encodedString1 = Base64.encodeToString(result, Base64.NO_WRAP)

        return encodedString1
    }
}