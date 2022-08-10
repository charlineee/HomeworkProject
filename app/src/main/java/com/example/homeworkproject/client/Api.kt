package com.example.homeworkproject.client

import com.example.homeworkproject.model.Country
import com.example.homeworkproject.model.Province
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("country")
    fun getCountry(): Call<ArrayList<Country>>

    @GET("country/{id}/province")
    fun getProvince(@Path("id") id: String?): Call<ArrayList<Province>>

}
