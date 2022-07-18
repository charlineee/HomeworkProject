package com.example.homeworkproject.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

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

    public MutableLiveData<ApiState<ArrayList<Country>>> getCountries(){
        final MutableLiveData<ApiState<ArrayList<Country>>> countryLiveData = new MutableLiveData<>();

        Call<ArrayList<Country>> call = apiRequest.getCountry();

        call.enqueue(new Callback<ArrayList<Country>>() {
            @Override
            public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {

                if (response.isSuccessful() && response.body() !=null){
                    //TODO: fix this
                    ArrayList<Country> country = response.body();
                    countryLiveData.setValue(ApiState.success(country));
                } }

            @Override
            public void onFailure(Call<ArrayList<Country>> call, Throwable t) {
                //TODO: fix this
                countryLiveData.setValue(ApiState.error(t.getMessage(), t.getMessage()));
            }
        });

        return countryLiveData;
    }

    public MutableLiveData<ApiState<ArrayList<Province>>> getProvince(String value) {

        final MutableLiveData<ApiState<ArrayList<Province>>> provinceLiveData = new MutableLiveData<>();
        Call<ArrayList<Province>> call = apiRequest.getProvince(value);
        call.enqueue(new Callback<ArrayList<Province>>() {
            @Override
            public void onResponse(Call<ArrayList<Province>> call, Response<ArrayList<Province>> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: Sending api call");
                    ArrayList<Province> province = response.body();
                    provinceLiveData.setValue(ApiState.success(province));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Province>> call, Throwable t) {
                provinceLiveData.setValue(ApiState.error(t.getMessage(), t.getMessage()));
            }
        });
        return provinceLiveData;
    }
}
