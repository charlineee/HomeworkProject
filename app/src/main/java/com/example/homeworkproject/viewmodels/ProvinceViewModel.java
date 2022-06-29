package com.example.homeworkproject.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.model.Province;
import com.example.homeworkproject.repository.Repository;

import java.util.ArrayList;

public class ProvinceViewModel extends ViewModel {
    Repository repository;

    public ProvinceViewModel(){
        repository = new Repository();
    }

    public LiveData<ArrayList<Province>> getLiveProvinceData(String id){
        return repository.getProvince(id);
    }
}