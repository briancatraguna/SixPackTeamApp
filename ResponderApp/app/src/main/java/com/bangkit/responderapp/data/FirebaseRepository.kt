package com.bangkit.responderapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FirebaseRepository(private val dataSource: FirebaseDataSource) {

    fun getAllReport(): MutableLiveData<ArrayList<ReportEntity?>>{
        return dataSource.getAllReports()
    }

    fun readSuccess(): LiveData<Boolean>{
        return dataSource.readSuccess
    }

    fun isLoading(): LiveData<Boolean>{
        return dataSource.isLoading
    }

    fun getReportIds(): MutableLiveData<ReportIdEntity?>{
        return dataSource.getReportIds()
    }

    fun updateStatus(reportId: String, report: ReportEntity?){
        dataSource.updateStatus(reportId,report)
    }

    fun getUpdateStatus():LiveData<Boolean>{
        return dataSource.updateFail
    }

}