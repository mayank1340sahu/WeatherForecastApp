package com.example.weatherforecastapp.network

import com.example.weatherforecastapp.Constant
import com.example.weatherforecastapp.newModel.NewWeather
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(value = "v1/current.json")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("aqi") units:String = "yes",
        @Query("key") Api_Key:String = Constant.API_KEY
    ): NewWeather
}