package com.dicoding.emergencyapp.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.emergencyapp.data.entity.ReportEntity
import com.dicoding.emergencyapp.databinding.ActivityDetailReportBinding

class DetailReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailReportBinding
    private var reportObject: ReportEntity? = null

    companion object {
        const val EXTRA_REPORT = "extra_report"
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

    }
}