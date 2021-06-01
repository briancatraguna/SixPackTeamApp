package com.dicoding.emergencyapp.data.repository

import androidx.lifecycle.LiveData
import com.dicoding.emergencyapp.data.remote.FirebaseDataSource

class FirebaseRepository(private val dataSource: FirebaseDataSource) {

    fun getUploadStatus(): LiveData<Boolean>{
        return dataSource.uploadStatus
    }

    fun uploadData(
        timestamp: String,
        userId: String?,
        transcription: String,
        report: String,
        latitude: Double?,
        longitude: Double?,
        status: String
    ){
        dataSource.uploadData(timestamp,userId,transcription,report,latitude,longitude,status)
    }
}