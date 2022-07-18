package com.example.homeworkproject.views;

import static com.example.homeworkproject.model.ApiState.Status.ERROR;
import static com.example.homeworkproject.model.ApiState.Status.LOADING;
import static com.example.homeworkproject.model.ApiState.Status.SUCCESS;

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
import com.example.homeworkproject.adapter.ProvinceAdapter;
import com.example.homeworkproject.databinding.FragmentCountriesBinding;
import com.example.homeworkproject.databinding.FragmentProvincesBinding;
import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.model.Province;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCountriesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initView(view);

        //set title on toolbar
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle("Countries");
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(false);

        return view;
    }

    private void initView(View view) {

        viewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        locationAdapter = new LocationAdapter(countryArrayList, getActivity(), this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(locationAdapter);

        getAllCountries();
    }

    public void getAllCountries() {
        viewModel.getLiveCountryData();

        binding.retryButton.setOnClickListener(view -> viewModel.getLiveCountryData());

        viewModel.countryData.observe(requireActivity(), country -> {

            switch(country.status){
                case SUCCESS:
                    locationAdapter.addList(country.data);
                    locationAdapter.notifyItemRangeChanged(0, (country.data).size());
                    break;
                case LOADING:
                    binding.progressBar.setVisibility(View.VISIBLE);
                    break;
                case ERROR:
                    binding.errorText.setVisibility(View.VISIBLE);
                    binding.retryButton.setVisibility(View.VISIBLE);
                    break;
            }

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