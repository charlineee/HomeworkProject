package com.example.homeworkproject.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkproject.R;
import com.example.homeworkproject.adapter.ProvinceAdapter;
import com.example.homeworkproject.model.Province;
import com.example.homeworkproject.viewmodels.LocationViewModel;

import java.util.ArrayList;

public class ProvincesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    LocationViewModel viewModel;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ProvinceAdapter provinceAdapter;
    private String mParam1;

    ArrayList<Province> provinceArrayList;

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
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        viewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        provinceAdapter = new ProvinceAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(provinceAdapter);
        provinceArrayList = new ArrayList<>();

        getAllProvinces(mParam1);
    }

    public void getAllProvinces(String value) {
        viewModel.getLiveProvinceData(value);
        viewModel.provinceData.observe(requireActivity(), provinceArrayList -> {
            progressBar.setVisibility(View.GONE);
            provinceAdapter.addList(provinceArrayList);
            provinceAdapter.notifyItemRangeChanged(0, viewModel.provinceData.getValue().size());
        });

    }
}