package com.example.homeworkproject.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkproject.R;
import com.example.homeworkproject.adapter.LocationAdapter;
import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.viewmodels.CountryViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;


public class CountriesFragment extends Fragment implements LocationAdapter.ItemClickListener {
RecyclerView recyclerView;
ProgressBar progressBar;
ArrayList<Country> countryArrayList;
LocationAdapter locationAdapter;

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
        View view= inflater.inflate(R.layout.fragment_countries, container, false);

        initView(view);

        //set title on toolbar
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle("Countries");
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(false);

        return view;
    }

    private void initView(View view) {

        //TODO: Let the viewModel hold onto this data versus the fragment
        countryArrayList = new ArrayList<>();

        ///TODO: Look to initiate your models before making ui changes / api calls
        getAllCountries();
        Log.d("TAG", "initView: ");
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        locationAdapter = new LocationAdapter(countryArrayList,getActivity(),this);
    }

    public void getAllCountries(){
        CountryViewModel viewModel = new ViewModelProvider(requireActivity()).get(CountryViewModel.class);
        viewModel.getLiveCountryData().observe(getViewLifecycleOwner(), countries -> {
            //TODO: Move progressBar.setvisibility out of if/else. Repeated code below.
            if (countries!= null){
                progressBar.setVisibility(View.GONE);
                countryArrayList.addAll(countries);
                //set layout/adapter for recyclerview
                //TODO: This should only be needed once in initView()
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(locationAdapter);
                //TODO: Notify adapter of new data received referencing viewModel
            }
            else{
                progressBar.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.countries_view),   ", try again?", Snackbar.LENGTH_LONG);
                snackbar.setAction("YES", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        });
    }

    @Override
    public void onItemClick(Country countryArrayList) {
        String value = String.valueOf(countryArrayList.getCountryId());

        Fragment fragment = ProvincesFragment.newInstance(value);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment, "provinces");
        transaction.addToBackStack("countries");
        transaction.commit();

    }

}