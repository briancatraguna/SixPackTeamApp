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

}