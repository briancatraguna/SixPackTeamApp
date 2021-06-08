package com.bangkit.responderapp.utils

import android.content.Context
import android.location.Geocoder
import java.util.*

class LocationHelper(private val context: Context) {
    fun getLocation(latitude: Double?, longitude: Double?): String{
        var cityName = ""
        var geoCoder = Geocoder(context, Locale.getDefault())
        var address = geoCoder.getFromLocation(latitude!!,longitude!!,1)
        cityName = address.get(0).locality

        return cityName
    }

    fun formatLocation(latitude: Double?, longitude: Double?):String{
        val cityName = getLocation(latitude,longitude)
        val result = """
            ${cityName}
            Latitude: ${latitude}
            Longitude: ${longitude}
        """.trimIndent()
        return result
    }
}