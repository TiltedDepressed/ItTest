package com.example.ittestapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ittestapplication.R
import com.example.ittestapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}