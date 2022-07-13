package com.example.homeworkproject.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.homeworkproject.model.ApiState;
import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.model.Province;
import com.example.homeworkproject.repository.Repository;

import java.util.ArrayList;

public class LocationViewModel extends ViewModel {

    private final Repository repository;
    //public MutableLiveData<ArrayList<Country>> countryData;
    public MutableLiveData<ArrayList<Province>> provinceData;
    public MutableLiveData<ApiState<ArrayList<Country>>> errorData;

    public LocationViewModel(){
        this.repository = new Repository();
        provinceData = new MutableLiveData<>();
    }

    /*public void getLiveCountryData() {
        if (countryData ==null){
            countryData = repository.getCountries();
        }
    }*/

    public void getLiveCountryData() {
        if (errorData ==null){
            errorData = repository.getCountries();
        }
    }

    public void getLiveProvinceData(String value){
        provinceData = repository.getProvince(value);

    }


}
