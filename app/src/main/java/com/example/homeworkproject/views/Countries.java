package com.example.homeworkproject.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkproject.R;
import com.example.homeworkproject.adapter.LocationAdapter;
import com.example.homeworkproject.client.Api;
import com.example.homeworkproject.client.RetrofitClient;
import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.model.Province;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Countries extends Fragment implements LocationAdapter.ItemClickListener {
RecyclerView recyclerView;
ProgressBar progressBar;
ArrayList<Country> countryArrayList;
LocationAdapter locationAdapter;
    String content = "";

    public Countries() {
        // Required empty public constructor
    }


    public static Countries newInstance() {

        return new Countries();
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
        locationAdapter = new LocationAdapter(countryArrayList,getActivity(),this);
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
                    countryArrayList.addAll(response.body()) ;

                        for (Country country : countryArrayList) {

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

    @Override
    public void onItemClick(Country countryArrayList) {
        Log.d("TAG", "onItemClick: " + countryArrayList.getCountryId());
        String value = String.valueOf(countryArrayList.getCountryId());

        Api apiRequest = RetrofitClient.getMyApi();
        Call<ArrayList<Province>> call = apiRequest.getProvince(value);
        call.enqueue(new Callback<ArrayList<Province>>() {
            @Override
            public void onResponse(Call<ArrayList<Province>> call, Response<ArrayList<Province>> response) {
                if (response.isSuccessful()){

                }
                ArrayList<Province> provinces = response.body();
                for (Province province : provinces) {
                    content += province.getProvinceName() + "\n";

                }
                //send content to fragment
                Fragment fragment = Provinces.newInstance(content);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameContainer, fragment, "provinces");
                transaction.addToBackStack("countries");
                transaction.commit();
            }

            @Override
            public void onFailure(Call<ArrayList<Province>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        content = "";
    }
}