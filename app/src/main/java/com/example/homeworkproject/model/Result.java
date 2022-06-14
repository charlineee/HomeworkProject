package com.example.homeworkproject.model;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("Name")
    private String countryName;

    public Result(String name){
        this.countryName = name;
    }

    public String getCountryName(){
        return countryName;
    }
}
