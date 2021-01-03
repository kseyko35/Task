package com.example.veriparktask.ui.view.fragment

import android.content.SharedPreferences
import android.util.Base64
import androidx.fragment.app.Fragment
import com.example.veriparktask.util.Constants
import com.example.veriparktask.functions.Decrypt
import javax.crypto.spec.SecretKeySpec



open class BaseFragment : Fragment() {
    internal fun decryption(symbol: String, prefences: SharedPreferences?): String? {

        val specDecode = Base64.decode(prefences?.getString(Constants.AESKEY, ""), Base64.NO_WRAP)
        val IVdecode = Base64.decode(prefences?.getString(Constants.AESIVKEY, ""), Base64.NO_WRAP)
        val keySpec = SecretKeySpec(specDecode, Constants.ALGORITHM)

        val decytDecode = Base64.decode(symbol, Base64.NO_WRAP)

        return Decrypt.decrypt(decytDecode, keySpec, IVdecode)
    }
}