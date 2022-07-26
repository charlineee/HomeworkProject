package com.example.homeworkproject.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.homeworkproject.client.Api;
import com.example.homeworkproject.client.RetrofitClient;
import com.example.homeworkproject.model.ApiState;
import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.model.Province;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    Api apiRequest = RetrofitClient.getMyApi();

    public MutableLiveData<ArrayList<Country>> getCountries(){
        final MutableLiveData<ArrayList<Country>> countryLiveData = new MutableLiveData<>();

        Call<ArrayList<Country>> call = apiRequest.getCountry();

        call.enqueue(new Callback<ArrayList<Country>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Country>> call, @NonNull Response<ArrayList<Country>> response) {

                if (response.isSuccessful() && response.body() !=null){

                    countryLiveData.setValue(response.body());
                } }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Country>> call, @NonNull Throwable t) {
                ApiState.error();
            }
        });

        return countryLiveData;
    }

    public MutableLiveData<ArrayList<Province>> getProvince(String value) {

        final MutableLiveData<ArrayList<Province>> provinceLiveData = new MutableLiveData<>();
        Call<ArrayList<Province>> call = apiRequest.getProvince(value);
        call.enqueue(new Callback<ArrayList<Province>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Province>> call, @NonNull Response<ArrayList<Province>> response) {
                if (response.isSuccessful()){
                    provinceLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Province>> call, @NonNull Throwable t) {
                ApiState.error();
            }
        });
        return provinceLiveData;
    }
}
