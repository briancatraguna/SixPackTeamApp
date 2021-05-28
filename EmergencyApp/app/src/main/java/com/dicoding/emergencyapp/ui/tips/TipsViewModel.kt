package com.dicoding.emergencyapp.ui.tips

import androidx.lifecycle.ViewModel
import com.dicoding.emergencyapp.data.entity.TipsEntity
import com.dicoding.emergencyapp.data.repository.TipsRepository

class TipsViewModel(private val repository: TipsRepository): ViewModel() {

    fun getTips(key: String): TipsEntity?{
        return repository.getTips(key)
    }

}