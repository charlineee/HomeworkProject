package com.example.homeworkproject.client;

import com.example.homeworkproject.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://connect.mindbodyonline.com/rest/worldregions/";
    @GET("country")
    Call<List<Result>> getCountry();
}
