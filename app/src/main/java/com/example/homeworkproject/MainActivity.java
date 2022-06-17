package com.example.homeworkproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkproject.adapter.LocationAdapter;
import com.example.homeworkproject.client.Api;
import com.example.homeworkproject.client.RetrofitClient;
import com.example.homeworkproject.model.Country;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Country> countryArrayList;
    private LocationAdapter locationAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        countryArrayList = new ArrayList<>();

        getAllCountries();

    }

    private void getAllCountries(){
        Api apiRequest = RetrofitClient.getMyApi();
        Call<ArrayList<Country>> call= apiRequest.getCountry();
        call.enqueue(new Callback<ArrayList<Country>>(){

            @Override
            public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    countryArrayList = response.body();

                    for (Country country : countryArrayList) {

                        locationAdapter = new LocationAdapter(countryArrayList, MainActivity.this);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(locationAdapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Country>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();
            }
        });
    }

}