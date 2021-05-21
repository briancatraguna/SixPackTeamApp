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

    fun getHoursAgo(rawDate: String?): String?{
        val dateOfArticle = cleanDate(rawDate)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateOfNow = current.format(formatter)

        return getDateDifference(dateOfArticle,dateOfNow)
    }

    private fun getDateDifference(currentDate: String?, pastDate: String?):String{
        val date1: java.util.Date
        val date2: java.util.Date
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        date1 = format.parse(currentDate)
        date2 = format.parse(pastDate)
        val difference: Long = date2.time - date1.time
        val seconds = difference/1000
        val minutes = seconds/60
        val hours = minutes/60
        val days = hours/24
        val months = days/30
        val result: String
        if (minutes<60){
            result = "$minutes minutes ago"
        } else if (hours<24){
            result = "$hours hours ago"
        } else if (days<30){
            result = "$days days ago"
        } else {
            result = "$months months ago"
        }
        return result
    }

}