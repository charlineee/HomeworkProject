package com.example.homeworkproject.client;

import com.example.homeworkproject.model.Province;
import com.example.homeworkproject.model.Country;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "https://connect.mindbodyonline.com/rest/worldregions/";
    @GET("country")
    Call<ArrayList<Country>> getCountry();

    @GET("country/{id}/province")
    Call<ArrayList<Province>> getProvince(@Path("id") String id);
}
