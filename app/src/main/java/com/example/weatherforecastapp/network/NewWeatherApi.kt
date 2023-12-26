package com.example.weatherforecastapp.network

import com.example.weatherforecastapp.Constant2
import com.example.weatherforecastapp.newModel.NewWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface NewWeatherApi {
    @GET(value = "/v1/current.json")
    suspend fun getNewWeather(
        @Query("q") query: String,
        @Query("aqi") units:String = "yes",
        @Query("key") appid:String = Constant2.API_KEY
    ): NewWeather
}
