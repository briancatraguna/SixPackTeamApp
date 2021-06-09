package com.dicoding.emergencyapp.ui.sos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.emergencyapp.data.entity.NerResponse
import com.dicoding.emergencyapp.data.repository.NerRepository

class NerViewModel(private val repository: NerRepository): ViewModel() {

    fun getResults(inputString: String):LiveData<NerResponse>{
        return repository.getResults(inputString)
    }

    fun isFail(): LiveData<Boolean>{
        return repository.isFail()
    }
}