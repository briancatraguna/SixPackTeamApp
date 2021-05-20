package com.dicoding.emergencyapp.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SheetRetrofitClient {
    private const val BASE_URL = "https://script.google.com/macros/s/AKfycbx1SHp58xOz1wzFA5OdD1AsYigqYehDyRQMdd0_ljUSCZ_VF5PI6aVPz7p7_j9azYf-/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val instance = retrofit.create(SheetApiService::class.java)
}