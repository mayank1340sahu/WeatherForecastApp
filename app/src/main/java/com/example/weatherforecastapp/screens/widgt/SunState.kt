package com.example.weatherforecastapp.screens.widgt

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.model.Weather
import com.example.weatherforecastapp.utils.formatTime

@Composable
fun SunState(weather: Weather) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Row() {
            Image( painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Humidity Icon",
                Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(3.dp))
            Text(text = "${ formatTime(weather.list[0].sunrise) } ", style = MaterialTheme.typography.bodyLarge)
        }
        Row() {
            Text(text = "${formatTime( weather.list[0].sunset) } ", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.width(3.dp))
            Image( painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Humidity Icon",
                Modifier.size(20.dp))
        }
    }
}
