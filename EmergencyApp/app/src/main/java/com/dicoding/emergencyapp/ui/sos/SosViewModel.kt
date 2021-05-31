package com.dicoding.emergencyapp.ui.sos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.emergencyapp.data.entity.PostResponse
import com.dicoding.emergencyapp.data.retrofit.SheetRetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SosViewModel: ViewModel() {
    private val _status = MutableLiveData<String>()
    fun getStatus(): LiveData<String> = _status

    fun postData(
            userId: String?,
            userPhoto: String?,
            transcription: String,
            report: String,
            latitude: Double?,
            longitude: Double?,
            status: String
    ){
        SheetRetrofitClient.instance
            .postData(userId,userPhoto,transcription,report,latitude,longitude,status)
            .enqueue(object : Callback<PostResponse>{
                override fun onResponse(
                    call: Call<PostResponse>,
                    response: Response<PostResponse>
                ) {
                    if (response.isSuccessful){
                        _status.postValue(response.body()?.status)
                    } else {
                        _status.postValue("Failed to send data")
                    }
                }

                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    _status.postValue("Failed to send data")
                }
            })
    }

    fun addToDatabase(
            userId: String?,
            userPhoto: String?,
            transcription: String,
            report: String,
            latitude: Double?,
            longitude: Double?,
            status: String
        ){


    }

}