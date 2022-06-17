package com.example.homeworkproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("Name")
    @Expose
    private String countryName;

    @SerializedName("Code")
    @Expose
    private String countryCode;

    @SerializedName("ID")
    @Expose
    private Integer countryId;

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getCountryId() {
        return countryId;
    }
}
