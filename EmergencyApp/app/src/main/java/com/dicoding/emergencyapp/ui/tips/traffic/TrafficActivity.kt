package com.dicoding.emergencyapp.ui.tips.traffic

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import com.dicoding.emergencyapp.R

class TrafficActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traffic)

        var videoTrailer = findViewById<VideoView>(R.id.trailer)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoTrailer)

        val onlineUrl : Uri = Uri.parse("https://youtu.be/1D-xWM677Co")

        videoTrailer.setMediaController(mediaController)
        videoTrailer.setVideoURI(onlineUrl)
        videoTrailer.requestFocus()
        videoTrailer.start()

    }
}