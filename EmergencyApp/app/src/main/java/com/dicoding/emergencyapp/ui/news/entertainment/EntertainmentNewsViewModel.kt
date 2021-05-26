package com.dicoding.emergencyapp.ui.news.entertainment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.emergencyapp.data.entity.ArticlesItem
import com.dicoding.emergencyapp.data.repository.NewsRepository

class EntertainmentNewsViewModel(private val repository: NewsRepository): ViewModel() {

    fun getEntertainmentNews(): LiveData<List<ArticlesItem?>>{
        return repository.getEntertainmentNews()
    }

    fun getStatus(): Boolean{
        return repository.entertainmentNewsLoadingStatus
    }

    fun isLoading(): LiveData<Boolean>{
        return repository.isLoadingEntertainment
    }

}