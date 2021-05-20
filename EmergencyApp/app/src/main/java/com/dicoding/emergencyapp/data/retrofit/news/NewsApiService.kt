package com.dicoding.emergencyapp.data.retrofit.news

import com.dicoding.emergencyapp.BuildConfig
import com.dicoding.emergencyapp.data.entity.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsApiService {
    @GET("top-headlines?country=id&category=health&apiKey=${BuildConfig.NEWS_TOKEN}")
    fun getHealthNews(
    ): Call<NewsResponse>

    @GET("top-headlines?country=id&category=science&apiKey=${BuildConfig.NEWS_TOKEN}")
    fun getScienceNews(
    ): Call<NewsResponse>

    @GET("top-headlines?country=id&category=entertainment&apiKey=${BuildConfig.NEWS_TOKEN}")
    fun getEntertainmentNews(
    ): Call<NewsResponse>

}
