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
    var loadHealthSuccess: Boolean = true

    private val _scienceNews = MutableLiveData<List<ArticlesItem?>>()
    val scienceNews: LiveData<List<ArticlesItem?>> = _scienceNews
    var loadScienceSuccess: Boolean = true

    private val _entertainmentNews = MutableLiveData<List<ArticlesItem?>>()
    val entertainmentNews: LiveData<List<ArticlesItem?>> = _entertainmentNews
    var loadEntertainmentSuccess: Boolean = true

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
        client.enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    val articles = response.body()?.articles
                    _healthNews.value = articles
                } else {
                    Log.e(TAG,"onFailure: ${response.message()}")
                    loadHealthSuccess = false
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG,"onFailure: ${t.message.toString()}")
                loadHealthSuccess = false
            }
        })
    }

    fun getScienceNews(){
        val client = NewsApiConfig.getApiService().getScienceNews()
        client.enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    val articles = response.body()?.articles
                    _scienceNews.value = articles
                } else {
                    Log.e(TAG,"onFailure: ${response.message()}")
                    loadScienceSuccess = false
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG,"onFailure: ${t.message.toString()}")
                loadScienceSuccess = false
            }
        })
    }

    fun getEntertainmentNews(){
        val client = NewsApiConfig.getApiService().getEntertainmentNews()
        client.enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    val articles = response.body()?.articles
                    _entertainmentNews.value = articles
                } else {
                    Log.e(TAG,"onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG,"onFailure: ${t.message.toString()}")
                loadEntertainmentSuccess = false
            }

        })
    }

}