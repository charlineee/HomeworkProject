package com.example.homeworkproject.viewmodels;

import static com.example.homeworkproject.model.ApiState.Status.LOADING;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.homeworkproject.model.ApiState;
import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.model.Province;
import com.example.homeworkproject.repository.Repository;

import java.util.ArrayList;

public class LocationViewModel extends ViewModel {

    private final Repository repository;
    public ApiState.Status currentState;
    public MutableLiveData<ArrayList<Province>> provinceData;
    public MutableLiveData<ArrayList<Country>> countryData;
    public String currentVal;

    public LocationViewModel(){
        this.repository = new Repository();
        currentState = LOADING;
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

    public Boolean isLoading(){
        return currentState == ApiState.Status.LOADING;
    }

    public Boolean isError(){
        return currentState == ApiState.Status.ERROR;
    }

}
