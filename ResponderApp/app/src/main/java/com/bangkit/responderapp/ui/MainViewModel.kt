package com.bangkit.responderapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.responderapp.data.FirebaseRepository
import com.bangkit.responderapp.data.ReportEntity

class MainViewModel(private val repository: FirebaseRepository):ViewModel() {

    fun getAllReports(): MutableLiveData<ArrayList<ReportEntity?>>{
        return repository.getAllReport()
    }

    fun readSuccess(): LiveData<Boolean>{
        return repository.readSuccess()
    }

}