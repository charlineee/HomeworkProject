package com.example.homeworkproject.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.model.Province;
import com.example.homeworkproject.repository.Repository;

import java.util.ArrayList;

public class ProvinceViewModel extends ViewModel {
    Repository repository;
    String id;

    public ProvinceViewModel(){
        repository = new Repository();
        this.id = id;
    }

    public LiveData<ArrayList<Province>> getLiveProvinceData(){
        return repository.getProvince(id);
    }
}