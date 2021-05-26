package com.dicoding.emergencyapp.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.emergencyapp.data.entity.ArticlesItem
import com.dicoding.emergencyapp.data.entity.NewsResponse
import com.dicoding.emergencyapp.data.retrofit.news.NewsApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsDataSource {

    private val _healthNews = MutableLiveData<List<ArticlesItem?>>()
    val healthNews: LiveData<List<ArticlesItem?>> = _healthNews
    private val _loadHealthSuccess = MutableLiveData<Boolean>()
    val loadHealthSuccess: LiveData<Boolean> = _loadHealthSuccess
    private val _isLoadingHealth = MutableLiveData<Boolean>()
    val isLoadingHealth: LiveData<Boolean> = _isLoadingHealth

    private val _scienceNews = MutableLiveData<List<ArticlesItem?>>()
    val scienceNews: LiveData<List<ArticlesItem?>> = _scienceNews
    private val _loadScienceSuccess = MutableLiveData<Boolean>()
    var loadScienceSuccess: LiveData<Boolean> = _loadScienceSuccess
    private val _isLoadingScience = MutableLiveData<Boolean>()
    val isLoadingScience: LiveData<Boolean> = _isLoadingScience

    private val _entertainmentNews = MutableLiveData<List<ArticlesItem?>>()
    val entertainmentNews: LiveData<List<ArticlesItem?>> = _entertainmentNews
    private val _loadEntertainmentSuccess = MutableLiveData<Boolean>()
    var loadEntertainmentSuccess: LiveData<Boolean> = _loadEntertainmentSuccess
    private val _isLoadingEntertainment = MutableLiveData<Boolean>()
    val isLoadingEntertainment: LiveData<Boolean> = _isLoadingEntertainment

    companion object {
        private var TAG = NewsDataSource::class.java.simpleName

        @Volatile
        private var instance: NewsDataSource? = null

        fun getInstance(): NewsDataSource =
            instance ?: synchronized(this){
                instance ?: NewsDataSource().apply {
                    instance = this
                }
            }
    }

    fun getHealthNews(){
        val client = NewsApiConfig.getApiService().getHealthNews()
        _isLoadingHealth.value = true
        _loadHealthSuccess.value = true
        client.enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    val articles = response.body()?.articles
                    _healthNews.value = articles
                    _isLoadingHealth.value = false
                } else {
                    Log.e(TAG,"onFailure: ${response.message()}")
                    _loadHealthSuccess.value = false
                    _isLoadingHealth.value = false
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG,"onFailure: ${t.message.toString()}")
                _loadHealthSuccess.value = false
                _isLoadingHealth.value = false
            }
        })
    }

    fun getScienceNews(){
        val client = NewsApiConfig.getApiService().getScienceNews()
        _isLoadingScience.value = true
        _loadScienceSuccess.value = true
        client.enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    val articles = response.body()?.articles
                    _scienceNews.value = articles
                    _isLoadingScience.value = false
                } else {
                    Log.e(TAG,"onFailure: ${response.message()}")
                    _loadScienceSuccess.value = false
                    _isLoadingScience.value = false
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG,"onFailure: ${t.message.toString()}")
                _loadScienceSuccess.value = false
                _isLoadingScience.value = false
            }
        })
    }

    fun getEntertainmentNews(){
        val client = NewsApiConfig.getApiService().getEntertainmentNews()
        _isLoadingEntertainment.value = true
        _loadEntertainmentSuccess.value = true
        client.enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    val articles = response.body()?.articles
                    _entertainmentNews.value = articles
                    _isLoadingEntertainment.value = false
                } else {
                    Log.e(TAG,"onFailure: ${response.message()}")
                    _loadEntertainmentSuccess.value = false
                    _isLoadingEntertainment.value = false
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG,"onFailure: ${t.message.toString()}")
                _loadEntertainmentSuccess.value = false
                _isLoadingEntertainment.value = false
            }

        })
    }

}