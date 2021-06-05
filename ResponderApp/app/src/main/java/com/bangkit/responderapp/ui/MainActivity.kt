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
            listReportsAdapter.setData(reports)
            rvReports.adapter = listReportsAdapter
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