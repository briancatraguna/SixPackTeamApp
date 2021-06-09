package com.bangkit.responderapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.responderapp.data.FirebaseRepository
import com.bangkit.responderapp.data.ReportEntity
import com.bangkit.responderapp.data.ReportIdEntity

class MainViewModel(private val repository: FirebaseRepository):ViewModel() {

    fun getAllReports(): MutableLiveData<ArrayList<ReportEntity?>>{
        return repository.getAllReport()
    }

    fun readSuccess(): LiveData<Boolean>{
        return repository.readSuccess()
    }

    fun isLoading(): LiveData<Boolean>{
        return repository.isLoading()
    }

    fun getReportIds(): MutableLiveData<ReportIdEntity?>{
        return repository.getReportIds()
    }

    fun updateStatus(reportId: String,report: ReportEntity){
        repository.updateStatus(reportId,report)
    }

    fun getUpdateStatus(): LiveData<Boolean>{
        return repository.getUpdateStatus()
    }

}