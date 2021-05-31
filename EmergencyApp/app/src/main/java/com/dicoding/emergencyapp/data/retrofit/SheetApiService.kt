package com.dicoding.emergencyapp.data.retrofit

import com.dicoding.emergencyapp.data.entity.PostResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SheetApiService {
    @FormUrlEncoded
    @POST("exec")
    fun postData(
            @Field("userId") userId: String?,
            @Field("userPhoto") userPhoto: String?,
            @Field("transcription") transcription: String,
            @Field("report") report: String,
            @Field("latitude") latitude: Double?,
            @Field("longitude") longitude: Double?,
            @Field("status") status: String
    ): Call<PostResponse>

}