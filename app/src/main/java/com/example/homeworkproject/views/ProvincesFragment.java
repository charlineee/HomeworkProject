package com.example.homeworkproject.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkproject.R;
import com.example.homeworkproject.adapter.ProvinceAdapter;
import com.example.homeworkproject.client.Api;
import com.example.homeworkproject.client.RetrofitClient;
import com.example.homeworkproject.model.Province;
import com.example.homeworkproject.viewmodels.ProvinceViewModel;
import com.google.android.material.snackbar.Snackbar;

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
        //set title and enable back button on toolbar
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Provinces");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        View view = inflater.inflate(R.layout.fragment_countries, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {

        provinceArrayList = new ArrayList<>();
        getAllProvinces(mParam1);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        provinceAdapter = new ProvinceAdapter(provinceArrayList);

    }

    private void getAllProvinces(String value) {
        Log.d("TAG", "getAllProvinces: " + value);
        ProvinceViewModel viewModel = new ViewModelProvider(requireActivity()).get(ProvinceViewModel.class);
        viewModel.getLiveProvinceData(value).observe(requireActivity(), new Observer<ArrayList<Province>>() {
            @Override
            public void onChanged(ArrayList<Province> provinces) {
                if (provinces!= null && provinces.size() > 0){
                    progressBar.setVisibility(View.GONE);
                    provinceArrayList.addAll(provinces);
                    Log.d("TAG", "onChanged: " + provinces.size());
                    //set layout/adapter for recyclerview

                } else{
                    progressBar.setVisibility(View.GONE);
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.frameContainer), "No provinces here", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(provinceAdapter);
            }
        });
    }
}