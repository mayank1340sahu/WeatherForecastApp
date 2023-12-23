package com.example.weatherforecastapp.view

import android.util.Log
import com.example.weatherforecastapp.model.Weather
import com.example.weatherforecastapp.network.WeatherApi
import com.example.weatherforecastapp.newModel.NewWeather
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api : WeatherApi) {

    suspend fun getWeather(cityQuery: String) : DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        }
        catch (e : Exception){
            Log.d("Exception", "getWeather: $e")
            return DataOrException(exception = e)
        }
        Log.d("Response", "getWeather: $response")
        return DataOrException(response)
    }
}