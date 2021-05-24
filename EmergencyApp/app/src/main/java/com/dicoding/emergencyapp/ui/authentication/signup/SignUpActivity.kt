package com.dicoding.emergencyapp.ui.authentication.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.button_back -> {
                finish()
            }
        }
    }
}