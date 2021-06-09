package com.bangkit.responderapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

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
    var status: String = ""
): Parcelable