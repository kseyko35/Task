package com.example.veriparktask.functions

import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec




object Decrypt {
    fun decrypt(
        cipherText: ByteArray?,
        key: SecretKey,
        IV: ByteArray?
    ): String? {
        try {
            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            val keySpec =
                SecretKeySpec(key.encoded, "AES")
            val ivSpec = IvParameterSpec(IV)
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
            val decryptedText = cipher.doFinal(cipherText)
            return String(decryptedText)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}
