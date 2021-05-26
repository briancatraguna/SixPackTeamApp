package com.dicoding.emergencyapp.ui.news.health

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.emergencyapp.data.entity.ArticlesItem
import com.dicoding.emergencyapp.data.repository.NewsRepository

class HealthNewsViewModel(private val repository: NewsRepository): ViewModel() {

    fun getHealthNews(): LiveData<List<ArticlesItem?>>{
        return repository.getHealthNews()
    }

    fun getStatus(): Boolean{
        return repository.healthNewsLoadingStatus
    }

    fun isLoading(): LiveData<Boolean>{
        return repository.isLoadingHealth
    }

}