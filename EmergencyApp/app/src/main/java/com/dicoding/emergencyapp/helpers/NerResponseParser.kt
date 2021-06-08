package com.dicoding.emergencyapp.helpers

import com.dicoding.emergencyapp.data.entity.NerResponse

class NerResponseParser(private val response: NerResponse) {

    fun getStringFormat(): String{
        val locationList = response.lOCATION
        val personList = response.pERSON
        val descriptionList = response.dESCRIPTION
        val weaponList = response.wEAPON
        val locationListString: String = "Location: ${locationList?.joinToString()}"
        val personListString: String = "Persons: ${personList?.joinToString()}"
        val descriptionListString: String = "Descriptions: ${descriptionList?.joinToString()}"
        val weaponListString: String = "Weapons involved: ${weaponList?.joinToString()}"

        val result = """
            ${locationListString}
            ${personListString}
            ${descriptionListString}
            ${weaponListString}
        """.trimIndent()


        return result
    }
}