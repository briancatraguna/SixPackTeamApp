package com.dicoding.emergencyapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}