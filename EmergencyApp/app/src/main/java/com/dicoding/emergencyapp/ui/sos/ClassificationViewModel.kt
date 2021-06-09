package com.dicoding.emergencyapp.ui.sos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.emergencyapp.data.entity.ClassificationResponse
import com.dicoding.emergencyapp.data.repository.ClassificationRepository

class ClassificationViewModel(private val repository: ClassificationRepository): ViewModel() {

    fun getResults(inputString: String): LiveData<ClassificationResponse>{
        return repository.getResults(inputString)
    }

    fun isFail(): LiveData<Boolean>{
        return repository.isFail()
    }

}