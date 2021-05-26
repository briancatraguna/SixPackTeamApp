package com.dicoding.emergencyapp.ui.news.entertainment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.emergencyapp.data.entity.ArticlesItem
import com.dicoding.emergencyapp.data.remote.NewsDataSource
import com.dicoding.emergencyapp.data.repository.NewsRepository
import com.dicoding.emergencyapp.databinding.FragmentNewsListBinding
import com.dicoding.emergencyapp.ui.news.ListNewsAdapter

class EntertainmentFragment : Fragment() {

    private lateinit var binding: FragmentNewsListBinding
    private val articlesObject = ArrayList<ArticlesItem?>()
    private var fail: Boolean = false

    companion object{
        private const val CATEGORY = "entertainment"
    }

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
        viewModel.getStatus().observe(requireActivity(),{success->
            if (!success){
                fail = true
                binding.tvFail.visibility = View.VISIBLE
            } else {
                fail = false
                binding.tvFail.visibility = View.GONE

            }
        })

        viewModel.getEntertainmentNews().observe(requireActivity(),{articles ->
            for (article in articles){
                articlesObject.add(article)
            }
        })

        viewModel.isLoading().observe(requireActivity(),{isLoading ->
            if (isLoading){
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                if (!fail){
                    listNewsAdapter.setData(articlesObject)
                    listNewsAdapter.setCategory(CATEGORY)
                    rvNews.adapter = listNewsAdapter
                } else {
                    listNewsAdapter.clearData()
                    rvNews.adapter = listNewsAdapter
                }
            }
        })
    }

}