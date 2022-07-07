package com.example.homeworkproject.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkproject.R;
import com.example.homeworkproject.adapter.LocationAdapter;
import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.viewmodels.LocationViewModel;

import java.util.ArrayList;
import java.util.Objects;


public class CountriesFragment extends Fragment implements LocationAdapter.ItemClickListener {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    public static ArrayList<Country> countryArrayList;
    LocationAdapter locationAdapter;
    public LocationViewModel viewModel;

    public CountriesFragment() {
        // Required empty public constructor
    }


    public static CountriesFragment newInstance() {

        return new CountriesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_countries, container, false);

        initView(view);

        //set title on toolbar
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle("Countries");
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(false);

        return view;
    }

    private void initView(View view) {

        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        viewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        locationAdapter = new LocationAdapter(countryArrayList, getActivity(), this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(locationAdapter);
        countryArrayList = new ArrayList<>();

        getAllCountries();
    }

    public void getAllCountries() {
        viewModel.getLiveCountryData();
        viewModel.countryData.observe(requireActivity(), countryArrayList -> {
            progressBar.setVisibility(View.GONE);
            locationAdapter.addList(countryArrayList);
            locationAdapter.notifyItemRangeChanged(0, viewModel.countryData.getValue().size());
        });

    }


    @Override
    public void onItemClick(Country countryDataArrayList) {
        String value = String.valueOf(countryDataArrayList.getCountryId());

        Fragment fragment = ProvincesFragment.newInstance(value);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment, "provinces");
        transaction.addToBackStack("countries");
        transaction.commit();
    }
}