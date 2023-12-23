package com.example.weatherforecastapp.screens.widgt

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.weatherforecastapp.model.Weather
import com.example.weatherforecastapp.newModel.NewWeather

@Composable
fun WeatherImage(weather: Weather, newWeather: NewWeather) {
    val string = "https://${newWeather.current.condition.icon}"
    Image(painter = rememberImagePainter(data = string),
        contentDescription ="Image", modifier = Modifier.size(80.dp))
}
