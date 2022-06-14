package com.example.homeworkproject.client;

import com.example.homeworkproject.model.ProvinceResult;
import com.example.homeworkproject.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "https://connect.mindbodyonline.com/rest/worldregions/";
    @GET("country")
    Call<List<Result>> getCountry();

    @GET("country/{id}/province")
    Call<List<ProvinceResult>> getProvince(@Path("id") String id);
}
