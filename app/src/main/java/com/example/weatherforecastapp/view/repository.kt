package com.example.weatherforecastapp.view

import android.util.Log
import com.example.weatherforecastapp.model.Weather
import com.example.weatherforecastapp.network.NewWeatherApi
import com.example.weatherforecastapp.network.WeatherApi
import com.example.weatherforecastapp.newModel.NewWeather
import com.example.weatherforecastapp.room.Unit
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api : WeatherApi,private val newApi: NewWeatherApi) {

    suspend fun getWeather(cityQuery: String, unit: String) : DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery,units =unit)
        }
        catch (e : Exception){
            Log.d("Exception", "getWeather: $e")
            return DataOrException(exception = e)
        }
        Log.d("Response", "getWeather: $response")
        return DataOrException(response)
    }
    suspend fun getNewWeather(cityQuery: String) : DataOrException<NewWeather, Boolean, Exception> {
        val response = try {
            newApi.getNewWeather(query = cityQuery)
        }
        catch (e : Exception){
            Log.d("Exception", "getWeather: $e")
            return DataOrException(exception = e)
        }
        Log.d("Response", "getIcon: $response")
        return DataOrException(response)
    }

}