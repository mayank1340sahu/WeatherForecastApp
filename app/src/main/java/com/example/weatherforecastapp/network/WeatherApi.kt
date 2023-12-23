package com.example.weatherforecastapp.network

import com.example.weatherforecastapp.Constant
import com.example.weatherforecastapp.Constant2
import com.example.weatherforecastapp.model.Weather
import com.example.weatherforecastapp.newModel.NewWeather
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("unit") units:String = "imperial",
        @Query("appid") appid:String = Constant.API_KEY
    ): Weather

}