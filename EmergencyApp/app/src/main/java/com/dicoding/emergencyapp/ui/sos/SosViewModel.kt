package com.dicoding.emergencyapp.ui.sos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.emergencyapp.data.entity.PostResponse
import com.dicoding.emergencyapp.data.entity.ReportEntity
import com.dicoding.emergencyapp.data.repository.FirebaseRepository
import com.dicoding.emergencyapp.data.retrofit.SheetRetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SosViewModel(private val repository: FirebaseRepository): ViewModel() {

    fun getStatus(): LiveData<Boolean>{
        return repository.getUploadStatus()
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
        repository.uploadData(usersName,usersPhoto,timestamp,userId,transcription,report,classification, latitude, longitude, status)
    }

    fun readUserReports(userId: String?): MutableLiveData<ArrayList<ReportEntity?>>{
        return repository.readUserReports(userId)
    }

    fun isReadSuccess(): LiveData<Boolean>{
        return repository.isReadSuccess()
    }

}