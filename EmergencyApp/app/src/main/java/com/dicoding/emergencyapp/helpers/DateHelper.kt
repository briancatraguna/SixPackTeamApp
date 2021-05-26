package com.dicoding.emergencyapp.helpers

import java.sql.Date
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.abs

object DateHelper {

    fun cleanDate(rawDate: String?): String?{
        var date = rawDate?.substring(0,rawDate.length-1)
        date = date?.replace("T"," ")
        return date
    }

}