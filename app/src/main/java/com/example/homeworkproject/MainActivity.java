package com.example.homeworkproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.homeworkproject.client.Api;
import com.example.homeworkproject.client.RetrofitClient;
import com.example.homeworkproject.model.ProvinceResult;
import com.example.homeworkproject.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Api myApi;
    private TextView myTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find views
        myTextView = findViewById(R.id.myTextview);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(Api.class);
        getCountry();
        getProvince();
    }

    private void getCountry(){
        Call<List<Result>> call= RetrofitClient.getMyApi().getCountry();
        call.enqueue(new Callback<List<Result>>(){

            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                if (!response.isSuccessful()) {
                    myTextView.setText(response.code());
                    return;
                }
                List<Result> results = response.body();

                for (Result result : results) {
                    String content = "";
                    content += "Name: " + result.getCountryName() + "\n";

                    myTextView.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                    myTextView.setText(t.toString());
            }
        });
    }

    private void getProvince(){

        //test province call, set to 38 canada

        Call<List<ProvinceResult>> call= RetrofitClient.getMyApi().getProvince("38");
        call.enqueue(new Callback<List<ProvinceResult>>(){

            @Override
            public void onResponse(Call<List<ProvinceResult>> call, Response<List<ProvinceResult>> response) {
                if (!response.isSuccessful()) {
                    myTextView.setText(response.code());
                    return;
                }
                List<ProvinceResult> provinces = response.body();

                for (ProvinceResult province : provinces) {
                    String content = "";
                    content += "Name: " + province.getProvinceName() + "\n";

                    myTextView.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<ProvinceResult>> call, Throwable t) {
                myTextView.setText(t.toString());
            }
        });
    }
}