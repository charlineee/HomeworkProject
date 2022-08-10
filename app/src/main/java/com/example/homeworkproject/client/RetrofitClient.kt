package com.example.homeworkproject.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var BASE_URL:String = "https://connect.mindbodyonline.com/rest/worldregions/"
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
    private val retrofit: Retrofit = retrofitBuilder.build()

    val myApi: Api = retrofit.create(Api::class.java)
}
