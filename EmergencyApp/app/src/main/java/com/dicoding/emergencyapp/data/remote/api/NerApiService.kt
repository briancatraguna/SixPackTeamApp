package com.dicoding.emergencyapp.data.remote.api


import com.dicoding.emergencyapp.data.entity.NerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NerApiService {
    @GET("?")
    fun getResults(
        @Query("input") input: String
    ): Call<NerResponse>

}