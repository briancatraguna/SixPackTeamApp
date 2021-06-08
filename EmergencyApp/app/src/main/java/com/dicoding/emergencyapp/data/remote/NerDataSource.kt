package com.dicoding.emergencyapp.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.emergencyapp.data.entity.NerResponse
import com.dicoding.emergencyapp.data.remote.api.NerApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NerDataSource {

    private val _isFail = MutableLiveData<Boolean>()
    val isFail: LiveData<Boolean> = _isFail

    private val _nerResponse = MutableLiveData<NerResponse>()
    val nerResponse: LiveData<NerResponse> = _nerResponse

    companion object {
        @Volatile
        private var instance: NerDataSource? = null

        fun getInstance(): NerDataSource =
            instance ?: synchronized(this){
                instance ?: NerDataSource().apply {
                    instance = this
                }
            }
    }

    fun getResults(inputString: String){
        _isFail.value = false
        val client = NerApiConfig.getApiService().getResults(inputString)
        client.enqueue(object: Callback<NerResponse> {
            override fun onResponse(call: Call<NerResponse>, response: Response<NerResponse>) {
                if (response.isSuccessful){
                    val result = response.body()
                    _nerResponse.value = result
                } else {
                    _isFail.value = true
                }
            }

            override fun onFailure(call: Call<NerResponse>, t: Throwable) {
                _isFail.value = true
            }

        })
    }

}