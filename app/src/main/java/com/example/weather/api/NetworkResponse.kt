package com.example.weather.api
// T reffers to weather model
 sealed class NetworkResponse<out T> {

     data class Success<out T>(val data : T) : NetworkResponse<T>()
     data class error(val message : String) : NetworkResponse<Nothing>()

    object Loading : NetworkResponse<Nothing>()
}