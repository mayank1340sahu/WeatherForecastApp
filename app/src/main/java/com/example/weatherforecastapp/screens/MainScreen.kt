package com.example.weatherforecastapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherforecastapp.model.Weather
import com.example.weatherforecastapp.view.DataOrException
import com.example.weatherforecastapp.view.WeatherViewModel

@Composable
fun MainScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    Column {
        val weatherData =
            produceState<DataOrException<Weather,Boolean,Exception>>(initialValue = DataOrException(loading = true))
            {
                value = viewModel.getWeatherData("Moscow")
            }.value

        if (weatherData.loading == true){
            CircularProgressIndicator()
        }
        else if(weatherData.data != null)
        {
            MainScaffold(weatherData.data!!)
        }
       
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(weather: Weather){
    Scaffold(topBar = {}) {
        MainContent(paddingValues = it,weather)
    }
}



@Composable
fun MainContent(paddingValues: PaddingValues, weather: Weather) {
    Text(text = weather.city.name,Modifier.padding(paddingValues))
}
