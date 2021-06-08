package com.dicoding.emergencyapp.data.entity

import com.google.gson.annotations.SerializedName

data class NerResponse(

	@field:SerializedName("LOCATION")
	val lOCATION: List<String?>? = null,

	@field:SerializedName("PERSON")
	val pERSON: List<Any?>? = null,

	@field:SerializedName("DESCRIPTION")
	val dESCRIPTION: List<Any?>? = null,

	@field:SerializedName("WEAPON")
	val wEAPON: List<Any?>? = null
)
