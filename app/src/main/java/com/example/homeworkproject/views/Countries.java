package com.example.homeworkproject.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.homeworkproject.R;
import com.example.homeworkproject.adapter.LocationAdapter;
import com.example.homeworkproject.client.Api;
import com.example.homeworkproject.client.RetrofitClient;
import com.example.homeworkproject.model.Country;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Countries extends Fragment {
RecyclerView recyclerView;
ProgressBar progressBar;
ArrayList<Country> countryArrayList;
LocationAdapter locationAdapter;
    public Countries() {
        // Required empty public constructor
    }


    public static Countries newInstance() {
        Countries fragment = new Countries();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_countries, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
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

                        locationAdapter = new LocationAdapter(countryArrayList, getActivity());
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(locationAdapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Country>> call, Throwable t) {
                Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
            }
        });
    }
}