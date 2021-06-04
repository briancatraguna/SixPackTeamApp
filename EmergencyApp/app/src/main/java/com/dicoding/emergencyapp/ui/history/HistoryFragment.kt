package com.dicoding.emergencyapp.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.data.entity.ReportEntity
import com.dicoding.emergencyapp.data.remote.FirebaseDataSource
import com.dicoding.emergencyapp.data.repository.FirebaseRepository
import com.dicoding.emergencyapp.databinding.FragmentHistoryBinding
import com.dicoding.emergencyapp.ui.sos.SosViewModel

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = SosViewModel(FirebaseRepository(FirebaseDataSource()))
        val userId = arguments?.getString("userId")

        val rvReports = binding.rvReports
        rvReports.layoutManager = LinearLayoutManager(context)
        val listReportsAdapter = ListReportsAdapter()

        viewModel.readUserReports(userId).observe(requireActivity(),{
            listReportsAdapter.setData(it)
            rvReports.adapter = listReportsAdapter
        })

        viewModel.isReadSuccess().observe(requireActivity(),{success->
            if (!success){
                binding.tvFail.visibility = View.VISIBLE
            } else {
                binding.tvFail.visibility = View.GONE
            }
        })
    }
}