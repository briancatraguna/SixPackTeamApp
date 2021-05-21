package com.dicoding.emergencyapp.ui.news.entertainment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.emergencyapp.data.remote.NewsDataSource
import com.dicoding.emergencyapp.data.repository.NewsRepository
import com.dicoding.emergencyapp.databinding.FragmentNewsListBinding
import com.dicoding.emergencyapp.ui.news.ListNewsAdapter

class EntertainmentFragment : Fragment() {

    private lateinit var binding: FragmentNewsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvNews = binding.rvNews
        rvNews.layoutManager = LinearLayoutManager(context)
        val listNewsAdapter = ListNewsAdapter()

        val viewModel = EntertainmentNewsViewModel(NewsRepository.getInstance(NewsDataSource()))
        val status = viewModel.getStatus()
        if (!status){
            binding.tvFail.visibility = View.VISIBLE
        } else {
            binding.tvFail.visibility = View.GONE
        }
        viewModel.getEntertainmentNews().observe(requireActivity(),{articles ->
            listNewsAdapter.setData(articles)
            rvNews.adapter = listNewsAdapter
        })
    }

}