package com.example.homeworkproject.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.homeworkproject.client.Api;
import com.example.homeworkproject.client.RetrofitClient;
import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.model.Province;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Repository {
    Api apiRequest = RetrofitClient.getMyApi();
    final MutableLiveData<ArrayList<Country>> countryLiveData = new MutableLiveData<>();
    final MutableLiveData<ArrayList<Province>> provinceLiveData = new MutableLiveData<>();

    public LiveData<ArrayList<Country>> getCountries(){

        Call<ArrayList<Country>> call = apiRequest.getCountry();
        call.enqueue(new Callback<ArrayList<Country>>() {
            @Override
            public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {
                if (response.isSuccessful()){
                    countryLiveData.setValue(response.body());
                    Log.d("TAG", "onResponse: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Country>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
        return countryLiveData;
    }

    public LiveData<ArrayList<Province>> getProvince(String value) {
        Call<ArrayList<Province>> call = apiRequest.getProvince(value);
        call.enqueue(new Callback<ArrayList<Province>>() {
            @Override
            public void onResponse(Call<ArrayList<Province>> call, Response<ArrayList<Province>> response) {
                if (response.isSuccessful()){
                    provinceLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Province>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
        return provinceLiveData;
    }
}
