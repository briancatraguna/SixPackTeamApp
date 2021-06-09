package com.bangkit.responderapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bangkit.responderapp.R
import com.bangkit.responderapp.data.FirebaseDataSource
import com.bangkit.responderapp.data.FirebaseRepository
import com.bangkit.responderapp.data.ReportEntity
import com.bangkit.responderapp.databinding.ActivityDetailReportBinding
import com.bangkit.responderapp.ui.maps.MapsActivity
import com.bangkit.responderapp.utils.LocationHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailReportBinding
    private var report: ReportEntity? = null
    private lateinit var viewModel: MainViewModel

    companion object{
        const val EXTRA_REPORT = "report"
        const val EXTRA_REPORT_ID = "report_id"
        private const val crime = "Crime"
        private const val medical = "Medical"
        private const val fire = "Fire"
        private const val naturalDisaster = "Natural Disaster"
        private const val trafficAccident = "Traffic Accident"
        const val LONGITUDE_KEY = "longitude"
        const val LATITUDE_KEY = "latitude"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailReportBinding.inflate(layoutInflater)
        report = intent.getParcelableExtra<ReportEntity>(EXTRA_REPORT)
        val id = intent.getStringExtra(EXTRA_REPORT_ID).toString()
        viewModel = MainViewModel(FirebaseRepository(FirebaseDataSource()))
        setContentView(binding.root)

        binding.toolbar.backBtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.tvDisplayName.text = report?.usersName
        binding.tvClassification.text = report?.classification
        binding.tvReport.text = report?.report
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
        binding.buttonMaps.setOnClickListener {
            val locationIntent = Intent(this,MapsActivity::class.java)
            locationIntent.putExtra(LONGITUDE_KEY,report?.longitude)
            locationIntent.putExtra(LATITUDE_KEY,report?.latitude)
            startActivity(locationIntent)
        }

        setupRadioGroupState()

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId){
                R.id.status_find -> {
                    report?.status = "Waiting for responder"
                    viewModel.updateStatus(id,report)
                    Toast.makeText(this,"Status updated succesfully!",Toast.LENGTH_SHORT).show()
                }
                R.id.status_progress -> {
                    report?.status = "Responder on progress"
                    viewModel.updateStatus(id,report)
                    Toast.makeText(this,"Status updated succesfully!",Toast.LENGTH_SHORT).show()
                }
                R.id.status_resolved -> {
                    report?.status = "Case resolved"
                    viewModel.updateStatus(id,report)
                    Toast.makeText(this,"Status updated succesfully!",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRadioGroupState() {
        val status = report?.status
        if (status == "Waiting for responder"){
            binding.statusFind.isChecked = true
        } else if (status == "Responder on progress"){
            binding.statusProgress.isChecked = true
        } else if (status == "Case resolved"){
            binding.statusResolved.isChecked = true
        }
    }
}