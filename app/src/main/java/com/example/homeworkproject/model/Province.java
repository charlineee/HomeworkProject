package com.example.homeworkproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Province {
    @SerializedName("Name")
    @Expose
    private final String provinceName;

    public Province(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceName(){
        return provinceName;
    }
}
