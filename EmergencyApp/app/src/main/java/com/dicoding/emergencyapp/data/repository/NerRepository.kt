package com.dicoding.emergencyapp.data.repository

import androidx.lifecycle.LiveData
import com.dicoding.emergencyapp.data.entity.NerResponse
import com.dicoding.emergencyapp.data.remote.NerDataSource

class NerRepository(private val dataSource: NerDataSource) {

    fun getResults(inputString: String): LiveData<NerResponse>{
        dataSource.getResults(inputString)
        return dataSource.nerResponse
    }

    fun isFail(): LiveData<Boolean>{
        return dataSource.isFail
    }

}