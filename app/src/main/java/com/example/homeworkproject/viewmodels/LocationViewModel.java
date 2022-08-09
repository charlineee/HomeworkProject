package com.example.homeworkproject.viewmodels;

import static com.example.homeworkproject.model.ApiState.Status.ERROR;
import static com.example.homeworkproject.model.ApiState.Status.LOADING;
import static com.example.homeworkproject.model.ApiState.Status.SUCCESS;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.homeworkproject.client.Api;
import com.example.homeworkproject.model.ApiState;
import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.model.Province;
import com.example.homeworkproject.repository.Repository;

import java.util.ArrayList;

public class LocationViewModel extends ViewModel {

    private final Repository repository;
    public final MutableLiveData<ApiState.Status> currentState = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Province>> provinceData;
    public MutableLiveData<ArrayList<Country>> countryData;
    public String currentVal;

    public LocationViewModel(){
        this.repository = new Repository();
    }

    public void getLiveCountryData() {

        if (countryData ==null || countryData.getValue() == null){
            currentState.setValue(LOADING);
            countryData = repository.getCountries();
        }
    }

    public void getLiveProvinceData(String value){
        if (!value.equals(currentVal) || provinceData.getValue() == null) {
            currentVal = value;
            currentState.setValue(LOADING);
            provinceData = repository.getProvince(value);

            }
    }

}
