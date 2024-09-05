package com.example.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.api.NetworkResponse
import com.example.weather.api.Retrofitinstance
import com.example.weather.api.Weathermodel
import com.example.weather.api.constant
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {


    private val weatherapi = Retrofitinstance.weatherapi
    private val _weatherResult = MutableLiveData<NetworkResponse<Weathermodel>>()
     val weatherResult: LiveData<NetworkResponse<Weathermodel>> = _weatherResult
    fun getData(city: String) {
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {

            try {

                val response = weatherapi.getWeather(constant.apikey, city)

                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    _weatherResult.value = NetworkResponse.error("Failed to load data")
                }
            } catch (e: Exception) {
                _weatherResult.value = NetworkResponse.error("Failed to load data")

            }

        }


    }
}

