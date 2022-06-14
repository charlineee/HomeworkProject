package com.example.homeworkproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.homeworkproject.client.RetrofitClient;
import com.example.homeworkproject.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCountry();
    }

    private void getCountry(){
        Call<List<Result>> call= RetrofitClient.getInstance().getMyApi().getCountry();
        call.enqueue(new Callback<List<Result>>(){

            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                List<Result> countryList = response.body();
                assert countryList !=null;
                String[] countries = new String[countryList.size()];

                for (int i = 0; i < countryList.size(); i++) {
                    countries[i] = countryList.get(i).getCountryName();
                    Log.d("TAG", "countries: " + countries[i]);
                }
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {

            }
        });
    }
}