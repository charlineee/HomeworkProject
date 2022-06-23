package com.example.homeworkproject.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkproject.R;
import com.example.homeworkproject.adapter.ProvinceAdapter;
import com.example.homeworkproject.client.Api;
import com.example.homeworkproject.client.RetrofitClient;
import com.example.homeworkproject.model.Province;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProvincesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ArrayList<Province> provinceArrayList;
    ProvinceAdapter provinceAdapter;
    private String mParam1;

    public ProvincesFragment() {
        // Required empty public constructor
    }


    public static ProvincesFragment newInstance(String param1) {
        ProvincesFragment fragment = new ProvincesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Provinces");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


        View view = inflater.inflate(R.layout.fragment_countries, container, false);
        initView(view);


        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        provinceArrayList = new ArrayList<>();
        provinceAdapter = new ProvinceAdapter(provinceArrayList);
        getAllProvinces(mParam1);
    }

    private void getAllProvinces(String value) {
        Api apiRequest = RetrofitClient.getMyApi();
        Call<ArrayList<Province>> call = apiRequest.getProvince(value);
        call.enqueue(new Callback<ArrayList<Province>>() {

            @Override
            public void onResponse(Call<ArrayList<Province>> call, Response<ArrayList<Province>> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    provinceArrayList.addAll(response.body());

                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(provinceAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Province>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}