package com.example.homeworkproject.viewmodels;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.homeworkproject.model.ApiState;
import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.model.Province;
import com.example.homeworkproject.repository.Repository;
import com.example.homeworkproject.views.CountriesFragment;

import java.util.ArrayList;

public class LocationViewModel extends ViewModel {

    private final Repository repository;

    public MutableLiveData<ApiState<ArrayList<Province>>> provinceData;
    public MutableLiveData<ApiState<ArrayList<Country>>> countryData;
    public String currentVal;

    public LocationViewModel(){
        this.repository = new Repository();
    }

    public void getLiveCountryData() {
        if (countryData ==null){
            countryData = repository.getCountries();
        }
    }

    public void getLiveProvinceData(String value){
        currentVal = value;
        provinceData = repository.getProvince(value);

    }


}
