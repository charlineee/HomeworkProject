package com.example.homeworkproject.repository

import androidx.lifecycle.MutableLiveData
import com.example.homeworkproject.client.RetrofitClient.myApi
import com.example.homeworkproject.model.Country
import com.example.homeworkproject.model.Province
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private var apiRequest = myApi

    fun getCountries(): MutableLiveData<ArrayList<Country>> {
        val countryLiveData= MutableLiveData<ArrayList<Country>>()
        val call: Call<ArrayList<Country>> = apiRequest.getCountry()
        call.enqueue(object: Callback<ArrayList<Country>>{
            override fun onResponse(
                call: Call<ArrayList<Country>>,
                response: Response<ArrayList<Country>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    countryLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ArrayList<Country>>, t: Throwable) {
                countryLiveData.value = null
            }
        })
        return countryLiveData
    }

    fun getProvinces(countryId: String?): MutableLiveData<ArrayList<Province>> {
        val provinceLiveData= MutableLiveData<ArrayList<Province>>()
        val call: Call<ArrayList<Province>> = apiRequest.getProvince(countryId)
        call.enqueue(object: Callback<ArrayList<Province>>{
            override fun onResponse(
                call: Call<ArrayList<Province>>,
                response: Response<ArrayList<Province>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    provinceLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ArrayList<Province>>, t: Throwable) {
                provinceLiveData.value = null
            }
        })
        return provinceLiveData
    }

}
