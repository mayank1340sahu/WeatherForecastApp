package com.example.weatherforecastapp.view

import androidx.lifecycle.ViewModel
import com.example.weatherforecastapp.newModel.NewWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {
   suspend fun getWeatherData(city: String):DataOrException<NewWeather,Boolean,Exception>{
       return repository.getWeather(city)
   }
}
