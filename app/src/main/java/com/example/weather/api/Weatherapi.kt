package com.example.weather.api

import com.example.weather.WeatherViewModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface Weatherapi {

    @GET("/v1/current.json")
    suspend fun  getWeather(
        @Query("key") apikey : String,
        @Query("q") city : String
    ) : Response<Weathermodel>


}