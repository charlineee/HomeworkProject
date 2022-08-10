package com.example.homeworkproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.homeworkproject.model.ApiState
import com.example.homeworkproject.model.Province
import com.example.homeworkproject.model.Country
import com.example.homeworkproject.repository.Repository
import java.util.ArrayList

class LocationViewModel : ViewModel() {
    private val repository: Repository = Repository()

    @JvmField
    val currentState = MutableLiveData<ApiState.Status>()
    var provinceData: MutableLiveData<ArrayList<Province>>? = null
    var countryData: MutableLiveData<ArrayList<Country>>? = null
    private var currentVal: String? = null

    fun getLiveCountryData() {
            if (countryData == null || countryData!!.value == null) {
                currentState.value = ApiState.Status.LOADING
                countryData = repository.getCountries()
            }
        }

    fun getLiveProvinceData(value: String?) {
        if (value != currentVal || provinceData!!.value == null) {
            currentVal = value
            currentState.value = ApiState.Status.LOADING
            provinceData = repository.getProvinces(value)
        }
    }

}
