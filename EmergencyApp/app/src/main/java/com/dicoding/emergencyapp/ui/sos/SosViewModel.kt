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

    fun postData(type: String,latitude: Double?,longitude: Double?,situation: String){
        SheetRetrofitClient.instance
            .postData(type,latitude,longitude,situation)
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

}