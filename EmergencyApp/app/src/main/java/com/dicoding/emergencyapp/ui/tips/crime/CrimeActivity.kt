package com.dicoding.emergencyapp.ui.tips.crime

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import com.dicoding.emergencyapp.R

class CrimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime)

        var videoTrailer = findViewById<VideoView>(R.id.trailer)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoTrailer)

        val onlineUrl : Uri = Uri.parse("https://youtu.be/0SbiGsd1gDc")

        videoTrailer.setMediaController(mediaController)
        videoTrailer.setVideoURI(onlineUrl)
        videoTrailer.requestFocus()
        videoTrailer.start()
    }
}