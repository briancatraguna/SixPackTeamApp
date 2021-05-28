package com.dicoding.emergencyapp.data.entity

import android.provider.MediaStore

data class TipsEntity(
    val title: String,
    val description: String,
    val tips: Array<String>,
    val video: Int
)
