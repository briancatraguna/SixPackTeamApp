package com.dicoding.emergencyapp.ui.authentication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.databinding.ActivityLoginBinding
import com.dicoding.emergencyapp.ui.authentication.signup.SignUpActivity
import com.dicoding.emergencyapp.ui.home.HomeActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener(this)
        binding.tvForgotPassword.setOnClickListener(this)
        binding.tvSignUp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id){

            R.id.button_login -> {
                val email = binding.edittextEmail.text.toString().trim()
                val password = binding.edittextPassword.text.toString().trim()
                if (email.equals("")){
                    binding.edittextEmail.error = "Enter email"
                } else if (password.equals("")){
                    binding.edittextPassword.error = "Enter password"
                } else {
                   val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
                   mAuth.signInWithEmailAndPassword(email,password)
                       .addOnCompleteListener(this, OnCompleteListener<AuthResult>(){
                           if (it.isSuccessful){
                               redirect()
                           } else {
                               Toast.makeText(this,"The email or password you entered is incorrect",Toast.LENGTH_SHORT).show()
                           }
                       })
                }
            }

            R.id.tv_forgot_password -> {

            }

            R.id.tv_sign_up -> {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun redirect() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}