package com.dicoding.emergencyapp.ui.news.health

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.data.remote.NewsDataSource
import com.dicoding.emergencyapp.data.repository.NewsRepository
import com.dicoding.emergencyapp.databinding.FragmentNewsListBinding
import com.dicoding.emergencyapp.ui.news.ListNewsAdapter

class HealthFragment : Fragment() {

    private lateinit var binding: FragmentNewsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvNews = binding.rvHealthNews
        rvNews.layoutManager = LinearLayoutManager(context)
        val listNewsAdapter = ListNewsAdapter()

        val viewModel = HealthNewsViewModel(NewsRepository.getInstance(NewsDataSource()))
        val healthNews = viewModel.getHealthNews()
        val status = viewModel.getStatus()
        viewModel.getHealthNews().observe(requireActivity(),{articles->
            listNewsAdapter.setData(articles)
            rvNews.adapter = listNewsAdapter
        })

    }

}