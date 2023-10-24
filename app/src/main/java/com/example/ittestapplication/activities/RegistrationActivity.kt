package com.example.ittestapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ittestapplication.R
import com.example.ittestapplication.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

 private lateinit var binding : ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}