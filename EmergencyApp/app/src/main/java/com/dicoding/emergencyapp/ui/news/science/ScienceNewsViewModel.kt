package com.dicoding.emergencyapp.ui.news.science

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.emergencyapp.data.entity.ArticlesItem
import com.dicoding.emergencyapp.data.repository.NewsRepository

class ScienceNewsViewModel(private val repository: NewsRepository): ViewModel() {

    fun getScienceNews(): LiveData<List<ArticlesItem?>>{
        return repository.getScienceNews()
    }

    fun getStatus(): LiveData<Boolean>{
        return repository.scienceNewsLoadingStatus
    }

    fun isLoading(): LiveData<Boolean>{
        return repository.isLoadingScience
    }

}