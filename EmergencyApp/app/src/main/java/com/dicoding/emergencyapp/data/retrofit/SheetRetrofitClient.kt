package com.dicoding.emergencyapp.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SheetRetrofitClient {
    private const val BASE_URL = "https://script.google.com/macros/s/AKfycbzOla63TYtkuvRFGPZO3VbkZyHhOLuKp7oILwF4hn2x7Xtk3JO9WtcKuDXttUedgoh1/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val instance = retrofit.create(SheetApiService::class.java)
}