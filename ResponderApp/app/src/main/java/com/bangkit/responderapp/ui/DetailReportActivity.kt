package com.bangkit.responderapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.responderapp.data.ReportEntity
import com.bangkit.responderapp.databinding.ActivityDetailReportBinding

class DetailReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailReportBinding
    private var report: ReportEntity? = null

    companion object{
        const val EXTRA_REPORT = "report"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailReportBinding.inflate(layoutInflater)
        report = intent.getParcelableExtra<ReportEntity>(EXTRA_REPORT)
        setContentView(binding.root)

        binding.tvTest.text = report?.usersName
    }
}