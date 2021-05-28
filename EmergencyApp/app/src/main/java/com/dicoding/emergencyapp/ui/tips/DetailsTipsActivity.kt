package com.dicoding.emergencyapp.ui.tips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.emergencyapp.databinding.ActivityDetailsTipsBinding

class DetailsTipsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsTipsBinding

    companion object{
        const val TOPIC_KEY = "topic_key"
        const val TRAFFIC_KEY = "traffic"
        const val CRIME_KEY = "crime"
        const val FIRE_KEY = "fire"
        const val LIFESTYLE_KEY = "lifestyle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsTipsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        populateView()
    }

    private fun populateView() {

    }
}