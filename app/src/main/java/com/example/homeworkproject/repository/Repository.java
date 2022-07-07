package com.example.homeworkproject.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.homeworkproject.client.Api;
import com.example.homeworkproject.client.RetrofitClient;
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
            public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {

                if (response.isSuccessful() && response.body()!=null){
                    countryLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Country>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });

        return countryLiveData;
    }

    public MutableLiveData<ArrayList<Province>> getProvince(String value) {

        final MutableLiveData<ArrayList<Province>> provinceLiveData = new MutableLiveData<>();
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
