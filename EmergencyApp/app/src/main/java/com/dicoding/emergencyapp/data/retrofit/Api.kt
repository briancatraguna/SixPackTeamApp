package com.dicoding.emergencyapp.data.retrofit

import com.dicoding.emergencyapp.data.entity.PostResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("exec")
    fun postData(
            @Field("type") type: String,
            @Field("latitude") latitude: Double?,
            @Field("longitude") longitude: Double?,
            @Field("situation") situation: String
    ): Call<PostResponse>

}