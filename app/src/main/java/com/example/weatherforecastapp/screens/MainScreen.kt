package com.example.weatherforecastapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherforecastapp.model.Weather
import com.example.weatherforecastapp.view.DataOrException
import com.example.weatherforecastapp.view.WeatherViewModel

@Composable
fun MainScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    Column {
        Text(text = "Main Screen")
        ShowData(mainViewModel = viewModel)
    }
}
@Composable
fun ShowData(mainViewModel: WeatherViewModel){
    val weatherData =
        produceState<DataOrException<Weather,Boolean,Exception>>(initialValue = DataOrException(loading = true))
        {
        value = mainViewModel.getWeatherData("Moscow")
    }.value

    if (weatherData.loading == true){
        CircularProgressIndicator()
    }
    else if(weatherData.data != null)
    {
        Text(text = weatherData.data!!.toString())
    }
}