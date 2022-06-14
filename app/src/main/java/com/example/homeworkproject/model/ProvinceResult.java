package com.example.homeworkproject.model;

import com.google.gson.annotations.SerializedName;

public class ProvinceResult {
    @SerializedName("Name")
    private final String provinceName;

    public ProvinceResult(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceName(){
        return provinceName;
    }
}
