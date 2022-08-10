package com.example.homeworkproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Country (

    @SerializedName("Name")
    @Expose
    val countryName: String,

    @SerializedName("Code")
    @Expose
    val countryCode: String,

    @SerializedName("ID")
    @Expose
    val countryId: Int
)


