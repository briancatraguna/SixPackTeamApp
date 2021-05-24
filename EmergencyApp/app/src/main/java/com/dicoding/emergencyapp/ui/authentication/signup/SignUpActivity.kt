package com.dicoding.emergencyapp.ui.authentication.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.databinding.ActivitySignUpBinding
import com.dicoding.emergencyapp.ui.authentication.login.LoginActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener(this)
        binding.tvLogin.setOnClickListener(this)
        binding.buttonSignup.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id){

            R.id.button_back -> {
                finish()
            }

            R.id.tv_login -> {
                finish()
            }

            R.id.button_signup -> {
                val email = binding.edittextEmail.text.toString().trim()
                val password = binding.edittextPassword.text.toString().trim()
                val confirmPassword = binding.edittextConfirmPassword.text.toString().trim()
                if (email.equals("")){
                    binding.edittextEmail.error = "Enter email"
                } else if (password.equals("")){
                    binding.edittextPassword.error = "Enter password"
                } else if (confirmPassword.equals("")){
                    binding.edittextConfirmPassword.error = "Confirm password"
                } else if (!confirmPassword.equals(password)){
                    binding.edittextConfirmPassword.error = "Password mismatched"
                } else {
                    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
                    mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(this, OnCompleteListener<AuthResult>(){
                            if (it.isSuccessful){
                                Toast.makeText(this,"Account succesfully registered",Toast.LENGTH_SHORT).show()
                                finish()
                            } else {
                                Toast.makeText(this,"Account failed to be registered: ${it.exception}",Toast.LENGTH_SHORT).show()
                            }
                        })
                }
            }
        }
    }
}