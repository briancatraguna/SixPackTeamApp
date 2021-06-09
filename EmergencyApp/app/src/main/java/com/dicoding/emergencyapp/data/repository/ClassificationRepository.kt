package com.dicoding.emergencyapp.data.repository

import androidx.lifecycle.LiveData
import com.dicoding.emergencyapp.data.entity.ClassificationResponse
import com.dicoding.emergencyapp.data.remote.ClassificationDataSource

class ClassificationRepository(private val dataSource: ClassificationDataSource) {

    fun getResults(inputString: String): LiveData<ClassificationResponse>{
        dataSource.getResults(inputString)
        return dataSource.classificationResponse
    }

    fun isFail(): LiveData<Boolean>{
        return dataSource.isFail
    }

}