package com.example.homeworkproject.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.repository.Repository;

import java.util.ArrayList;

public class CountryViewModel extends ViewModel {
    Repository repository;

    public CountryViewModel(){
        repository = new Repository();
    }

    public LiveData<ArrayList<Country>> getLiveCountryData(){
        return repository.getCountries();
    }
}
