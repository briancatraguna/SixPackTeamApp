package com.dicoding.emergencyapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.emergencyapp.data.entity.ReportEntity
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

    fun readUserReports(userId: String?): MutableLiveData<ArrayList<ReportEntity?>>{
        return dataSource.readUserReports(userId)
    }
}