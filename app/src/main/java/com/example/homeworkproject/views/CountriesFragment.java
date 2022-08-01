package com.example.homeworkproject.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.homeworkproject.R;
import com.example.homeworkproject.adapter.LocationAdapter;
import com.example.homeworkproject.databinding.FragmentCountriesBinding;
import com.example.homeworkproject.model.ApiState;
import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.viewmodels.LocationViewModel;

import java.util.ArrayList;
import java.util.Objects;


public class CountriesFragment extends Fragment implements LocationAdapter.ItemClickListener {

    public static ArrayList<Country> countryArrayList;
    private LocationAdapter locationAdapter;
    public LocationViewModel viewModel;
    private FragmentCountriesBinding binding;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCountriesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initView();

        //set title on toolbar
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.c_title);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(false);

        return view;
    }

    private void initView() {

        viewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        locationAdapter = new LocationAdapter(countryArrayList, getActivity(), this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(locationAdapter);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setCountriesFragment(this);
        getAllCountries();
    }



    public void getAllCountries() {
        viewModel.getLiveCountryData();

        viewModel.countryData.observe(requireActivity(), country -> {
            if(country == null){
                viewModel.currentState.setValue(ApiState.Status.ERROR);
            } else {
                viewModel.currentState.setValue(ApiState.Status.SUCCESS);
                locationAdapter.addList(country);
                locationAdapter.notifyItemRangeChanged(0, (country.size()));
            }
        });

    }


    @Override
    public void onItemClick(Country selected) {
        String value = String.valueOf(selected.getCountryId());

        Fragment fragment = ProvincesFragment.newInstance(value);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment, "provinces");
        transaction.addToBackStack("countries");
        transaction.commit();
    }
}