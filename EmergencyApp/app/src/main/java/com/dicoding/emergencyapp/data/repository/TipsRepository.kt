package com.dicoding.emergencyapp.data.repository

import com.dicoding.emergencyapp.data.entity.TipsEntity
import com.dicoding.emergencyapp.data.local.TipsLocalDataSource

class TipsRepository(private val dataSource: TipsLocalDataSource) {

    companion object{
        @Volatile
        private var instance: TipsRepository? = null

        fun getInstance(dataSource: TipsLocalDataSource) =
            instance ?: synchronized(this){
                instance ?: TipsRepository(dataSource).apply {
                    instance = this
                }
            }
    }

    fun getTips(key: String):TipsEntity?{
        return dataSource.getTips(key)
    }

}