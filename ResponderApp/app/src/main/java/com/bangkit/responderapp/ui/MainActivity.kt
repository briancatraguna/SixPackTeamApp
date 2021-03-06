package com.bangkit.responderapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.responderapp.data.FirebaseDataSource
import com.bangkit.responderapp.data.FirebaseRepository
import com.bangkit.responderapp.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = MainViewModel(FirebaseRepository(FirebaseDataSource()))
        setContentView(binding.root)

        populateView()
    }

    private fun populateView() {
        val rvReports = binding.rvReports
        rvReports.layoutManager = LinearLayoutManager(this)
        val listReportsAdapter = ListReportsAdapter(this)
        viewModel.getAllReports().observe(this,{reports->
            if (reports.size>0){
                listReportsAdapter.setData(reports)
            }
            viewModel.getReportIds().observe(this,{ids->
                if (ids?.ids!=null){
                    listReportsAdapter.setReportIds(ids)
                    if (reports.size>0){
                        rvReports.adapter = listReportsAdapter
                    }
                }
            })

        })

        viewModel.isLoading().observe(this,{isLoading->
            if (isLoading){
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        viewModel.readSuccess().observe(this,{success->
            if (!success){
                binding.tvFail.visibility = View.VISIBLE
            } else {
                binding.tvFail.visibility = View.GONE
            }
        })
    }
}