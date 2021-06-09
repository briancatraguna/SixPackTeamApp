package com.dicoding.emergencyapp.ui.history

import android.content.Intent
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.data.entity.ReportEntity
import com.dicoding.emergencyapp.databinding.ActivityDetailReportBinding
import com.dicoding.emergencyapp.ui.googlemapsactivity.MapsActivity
import java.util.*

class DetailReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailReportBinding
    private var reportObject: ReportEntity? = null

    companion object {
        const val EXTRA_REPORT = "extra_report"
        private const val LONGITUDE_KEY = "longitude"
        private const val LATITUDE_KEY = "latitude"
        private const val CITY_KEY = "city"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reportObject = intent.getParcelableExtra<ReportEntity>(EXTRA_REPORT)

        binding.toolbar.backBtn.setOnClickListener{
            finish()
        }

        populateView()
    }

    private fun populateView() {
        binding.tvEmergencyType.text = reportObject?.classification
        binding.tvDate.text = reportObject?.date
        binding.tvTranscription.text = reportObject?.transcription
        binding.tvReport.text = reportObject?.report
        binding.buttonLocation.setOnClickListener {
            val locationIntent = Intent(this,MapsActivity::class.java)
            locationIntent.putExtra(LONGITUDE_KEY,reportObject?.longitude)
            locationIntent.putExtra(LATITUDE_KEY,reportObject?.latitude)
            val cityName = getCityName(reportObject?.latitude,reportObject?.longitude)
            locationIntent.putExtra(CITY_KEY,cityName)
            startActivity(locationIntent)
        }
        if (reportObject?.status == "Waiting for responder"){
            binding.toolbar.imgStatus.setImageResource(R.drawable.ic_status_finding_responder)
        } else if (reportObject?.status == "Responder on progress"){
            binding.toolbar.imgStatus.setImageResource(R.drawable.ic_status_on_progress)
        } else if (reportObject?.status == "Case resolved"){
            binding.toolbar.imgStatus.setImageResource(R.drawable.ic_status_resolved)
        }
        binding.toolbar.imgStatus
    }

    private fun getCityName(lat:Double?,long: Double?):String{
        var cityName = ""
        var geoCoder = Geocoder(this, Locale.getDefault())
        if (lat!=null && long!=null){
            var address = geoCoder.getFromLocation(lat,long,1)
            cityName = address.get(0).locality
        }
        return cityName
    }
}