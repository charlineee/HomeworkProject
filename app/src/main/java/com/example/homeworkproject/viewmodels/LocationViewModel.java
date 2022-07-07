package com.example.homeworkproject.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.model.Province;
import com.example.homeworkproject.repository.Repository;

import java.util.ArrayList;

public class LocationViewModel extends ViewModel {

    private final Repository repository;
    public MutableLiveData<ArrayList<Country>> countryData;
    public MutableLiveData<ArrayList<Province>> provinceData;

    public LocationViewModel(){
        this.repository = new Repository();

    }

    public void getLiveCountryData() {
        if (countryData ==null){
            countryData = repository.getCountries();
        }
    }

    public void getLiveProvinceData(String value){
        provinceData = repository.getProvince(value);

    }


}
