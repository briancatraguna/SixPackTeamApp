package com.dicoding.emergencyapp.ui.tips

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import com.dicoding.emergencyapp.data.local.TipsLocalDataSource
import com.dicoding.emergencyapp.data.repository.TipsRepository
import com.dicoding.emergencyapp.databinding.ActivityDetailsTipsBinding
import kotlinx.android.synthetic.main.activity_details_tips.*

class DetailsTipsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsTipsBinding
    private var mediaController: MediaController? = null

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

        binding.toolbar.backBtn.setOnClickListener {
            finish()
        }

        populateView()
    }

    private fun populateView() {
        val viewModel = TipsViewModel(TipsRepository.getInstance(TipsLocalDataSource(this)))
        val key = intent.getStringExtra(TOPIC_KEY)
        val videoView = binding.videoView
        if (mediaController == null){
            mediaController = MediaController(this)
            mediaController!!.setAnchorView(this.videoView)
        }
        videoView.setMediaController(mediaController)
        if (key!=null){
            val tips = viewModel.getTips(key)
            binding.toolbar.tvTitle.text = tips?.title
            binding.tvDescription.text = tips?.description
            val uri = "android.resource://"+packageName+"/"+tips?.video
            videoView!!.setVideoURI(Uri.parse(uri))
            videoView!!.requestFocus()
            videoView!!.start()

            var tipsString = ""
            val listTips = tips?.tips
            var count = 1
            for (tip in listTips!!){
                tipsString += "${count}. ${tip}\n"
                count += 1
            }
            binding.listTips.text = tipsString
        }
    }
}