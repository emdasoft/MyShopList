package com.emdasoft.myshoplist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.emdasoft.myshoplist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}