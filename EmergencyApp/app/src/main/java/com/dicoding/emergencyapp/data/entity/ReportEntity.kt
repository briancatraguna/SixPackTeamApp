package com.dicoding.emergencyapp.data.entity

data class ReportEntity (
    val date: String,
    val transcription: String,
    val report: String,
    val latitude: Double?,
    val longitude: Double?,
    val status: String
)