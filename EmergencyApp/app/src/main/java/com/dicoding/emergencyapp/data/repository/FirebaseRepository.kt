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
        usersName: String,
        usersPhoto: String,
        timestamp: String,
        userId: String?,
        transcription: String,
        report: String,
        classification: String,
        latitude: Double?,
        longitude: Double?,
        status: String
    ){
        dataSource.uploadData(usersName,usersPhoto,timestamp,userId,transcription,report,classification,latitude,longitude,status)
    }

    fun readUserReports(userId: String?): MutableLiveData<ArrayList<ReportEntity?>>{
        return dataSource.readUserReports(userId)
    }

    fun isReadSuccess(): LiveData<Boolean>{
        return dataSource.readSuccess
    }
}