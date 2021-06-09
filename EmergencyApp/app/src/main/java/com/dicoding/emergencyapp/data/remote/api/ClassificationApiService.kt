package com.dicoding.emergencyapp.data.remote.api

import com.dicoding.emergencyapp.data.entity.ClassificationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ClassificationApiService {
    @GET("?")
    fun getResults(
        @Query("input") input: String
    ): Call<ClassificationResponse>

}