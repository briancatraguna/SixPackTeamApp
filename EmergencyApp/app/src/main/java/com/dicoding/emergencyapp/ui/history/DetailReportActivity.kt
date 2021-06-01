package com.dicoding.emergencyapp.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.emergencyapp.databinding.ActivityDetailReportBinding

class DetailReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.backBtn.setOnClickListener{
            finish()
        }

    }
}