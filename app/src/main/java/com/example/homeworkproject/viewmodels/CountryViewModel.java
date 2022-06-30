package com.example.homeworkproject.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.repository.Repository;

import java.util.ArrayList;

public class CountryViewModel extends ViewModel {
    Repository repository;

    //TODO: Add MutableLiveData<ArrayList<Country>> countryList object;

    public CountryViewModel(){
        repository = new Repository();
    }

    //TODO: Look to change to a void function and just update live data object here in viewModel
    //TODO: once you receive data from the repository
    public LiveData<ArrayList<Country>> getLiveCountryData(){
        return repository.getCountries();
    }
}
