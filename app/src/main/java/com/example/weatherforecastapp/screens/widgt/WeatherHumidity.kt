package com.example.weatherforecastapp.screens.widgt

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.model.Weather

@Composable
fun WeatherHumidity(weather: Weather) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Row() {
            Image( painter = painterResource(id = R.drawable.humidity),
                contentDescription = "Humidity Icon",
                Modifier.size(20.dp))
            Text(text = "${ weather.list[0].humidity }%", style = MaterialTheme.typography.bodyLarge)

        }

        Row() {
            Image( painter = painterResource(id = R.drawable.thermometer),
                contentDescription = "Humidity Icon",
                Modifier.size(20.dp))
            Text(text = "${ weather.list[0].pressure } psl", style = MaterialTheme.typography.bodyLarge)

        }

        Row() {
            Image( painter = painterResource(id = R.drawable.wind),
                contentDescription = "Humidity Icon",
                Modifier.size(20.dp))
            Text(text = "${ weather.list[0].speed } kph", style = MaterialTheme.typography.bodyLarge)

        }
    }
}
