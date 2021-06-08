package com.bangkit.responderapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bangkit.responderapp.R
import com.bangkit.responderapp.data.ReportEntity
import com.bangkit.responderapp.databinding.ActivityDetailReportBinding
import com.bangkit.responderapp.utils.LocationHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailReportBinding
    private var report: ReportEntity? = null

    companion object{
        const val EXTRA_REPORT = "report"
        private const val crime = "Crime"
        private const val medical = "Medical"
        private const val fire = "Fire"
        private const val naturalDisaster = "Natural Disaster"
        private const val trafficAccident = "Traffic Accident"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailReportBinding.inflate(layoutInflater)
        report = intent.getParcelableExtra<ReportEntity>(EXTRA_REPORT)
        setContentView(binding.root)

        binding.toolbar.backBtn.setOnClickListener { finish() }

        binding.tvDisplayName.text = report?.usersName
        binding.tvClassification.text = report?.classification
        val reportClass = report?.classification
        if (reportClass == crime){
            binding.imgLogo.setImageResource(R.drawable.ic_crime_logo)
        } else if (reportClass == medical){
            binding.imgLogo.setImageResource(R.drawable.ic_medical_logo)
        } else if (reportClass == fire){
            binding.imgLogo.setImageResource(R.drawable.ic_fire_logo)
        } else if (reportClass == naturalDisaster){
            binding.imgLogo.setImageResource(R.drawable.ic_natural_disaster_logo)
        } else if (reportClass == trafficAccident){
            binding.imgLogo.setImageResource(R.drawable.ic_traffic_accident_logo)
        } else {
            binding.imgLogo.setImageResource(R.drawable.ic_unknown_logo)
        }
        val locationHelper = LocationHelper(this)
        binding.tvLocation.text = locationHelper.formatLocation(report?.latitude,report?.longitude)
        binding.tvTime.text = report?.date
        binding.tvTranscription.text = report?.transcription
        binding.imgProgressBar.visibility = View.VISIBLE
        Glide.with(this)
            .load(report?.usersPhoto.toString())
            .apply(RequestOptions().override(500,500))
            .into(binding.imgPerson)
        binding.imgProgressBar.visibility = View.GONE
    }
}