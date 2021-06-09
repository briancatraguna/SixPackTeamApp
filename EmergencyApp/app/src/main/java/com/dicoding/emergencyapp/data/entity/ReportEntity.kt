package com.dicoding.emergencyapp.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReportEntity (
    val usersName: String = "",
    val usersPhoto: String = "",
    val date: String = "",
    val transcription: String = "",
    val report: String = "",
    val classification: String = "",
    val latitude: Double? = 0.0,
    val longitude: Double? = 0.0,
    val status: String = ""
): Parcelable