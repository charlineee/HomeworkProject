package com.example.homeworkproject.model;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("Name")
    private String countryName;

    @SerializedName("Code")
    private String countryCode;

    @SerializedName("ID")
    private int countryId;

    public Result(String countryName, String countryCode, int countryId) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.countryId = countryId;
    }

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
