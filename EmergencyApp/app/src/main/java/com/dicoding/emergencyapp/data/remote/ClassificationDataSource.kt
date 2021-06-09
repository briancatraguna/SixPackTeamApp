package com.dicoding.emergencyapp.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.emergencyapp.data.entity.ClassificationResponse
import com.dicoding.emergencyapp.data.remote.api.ClassificationApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClassificationDataSource {

    private val _isFail = MutableLiveData<Boolean>()
    val isFail: LiveData<Boolean> = _isFail

    private val _classificationResponse = MutableLiveData<ClassificationResponse>()
    val classificationResponse: LiveData<ClassificationResponse> = _classificationResponse

    companion object {
        @Volatile
        private var instance: ClassificationDataSource? = null

        fun getInstance(): ClassificationDataSource =
            instance ?: synchronized(this){
                instance ?: ClassificationDataSource().apply {
                    instance = this
                }
            }
    }

    fun getResults(inputString: String){
        _isFail.value = false
        val client = ClassificationApiConfig.getApiService().getResults(inputString)
        client.enqueue(object: Callback<ClassificationResponse>{
            override fun onResponse(
                call: Call<ClassificationResponse>,
                response: Response<ClassificationResponse>
            ) {
                if (response.isSuccessful){
                    val result = response.body()
                    _classificationResponse.value = result
                } else {
                    _isFail.value = true
                }
            }

            override fun onFailure(call: Call<ClassificationResponse>, t: Throwable) {
                _isFail.value = true
            }

        })
    }

}