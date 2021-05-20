package com.dicoding.emergencyapp.data.repository

import androidx.lifecycle.LiveData
import com.dicoding.emergencyapp.data.entity.ArticlesItem
import com.dicoding.emergencyapp.data.remote.NewsDataSource

class NewsRepository(private val newsDataSource: NewsDataSource) {

    companion object {
        @Volatile
        private var instance: NewsRepository? = null

        fun getInstance(newsDataSource: NewsDataSource) =
            instance ?: synchronized(this){
                instance ?: NewsRepository(newsDataSource).apply {
                    instance = this
                }
            }
    }

    fun getHealthNews(): LiveData<List<ArticlesItem?>>{
        newsDataSource.getHealthNews()
        return newsDataSource.healthNews
    }

    val healthNewsLoadingStatus: Boolean = newsDataSource.loadHealthSuccess

    fun getScienceNews(): LiveData<List<ArticlesItem?>>{
        newsDataSource.getScienceNews()
        return newsDataSource.scienceNews
    }

    val scienceNewsLoadingStatus: Boolean = newsDataSource.loadScienceSuccess

    fun getEntertainmentNews(): LiveData<List<ArticlesItem?>>{
        newsDataSource.getEntertainmentNews()
        return newsDataSource.entertainmentNews
    }

    val entertainmentNewsLoadingStatus: Boolean = newsDataSource.loadEntertainmentSuccess

}