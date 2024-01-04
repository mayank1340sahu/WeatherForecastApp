package com.example.weatherforecastapp.view

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.weatherforecastapp.model.Weather
import com.example.weatherforecastapp.newModel.NewWeather
import com.example.weatherforecastapp.room.Unit
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    val cityState : MutableState<String> = mutableStateOf("Raipur")
   suspend fun getWeatherData(city: String, unit: String):DataOrException<Weather,Boolean,Exception>{
       return repository.getWeather(city,unit)
   }
    suspend fun getNewWeatherData(city: String):DataOrException<NewWeather,Boolean,Exception>{
        return repository.getNewWeather(city)
    }
}
