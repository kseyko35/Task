package com.example.veriparktask.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.veriparktask.databinding.ActivityMainBinding
import com.example.veriparktask.network.action.AuthAction



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "IMKB Hisse ve Endeksler"


        AuthAction(application)


        binding.firstButton.setOnClickListener {
            val intent = Intent(this, NavDrawActivity::class.java)
            startActivity(intent)
        }

    }
}