package com.dicoding.emergencyapp.data.entity

data class ReportEntity (
    val date: String = "",
    val transcription: String = "",
    val report: String = "",
    val latitude: Double? = 0.0,
    val longitude: Double? = 0.0,
    val status: String = ""
)