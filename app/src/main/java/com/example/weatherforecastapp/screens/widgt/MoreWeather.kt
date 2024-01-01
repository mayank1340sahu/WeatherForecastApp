package com.example.weatherforecastapp.screens.widgt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.model.Weather
import com.example.weatherforecastapp.model.WeatherItem
import com.example.weatherforecastapp.utils.formatDay
import com.example.weatherforecastapp.utils.formatDecimals
import com.example.weatherforecastapp.utils.formatTemp

@Composable
fun WeekWeather(weather: Weather) {
    Row(Modifier.fillMaxWidth(),Arrangement.Center) {
        Text(text = "This Week")
    }
    Divider()
    LazyColumn(content ={
        items(weather.list){
            if (weather.list.indexOf(it) != 0){
                DayWeather(weather = it)
            }
        }
    } )
}

@Composable
fun DayWeather(weather: WeatherItem) {

    Card(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(topStart = 0.dp,0.dp,23.dp,23.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Row(Modifier.fillMaxWidth(),Arrangement.SpaceBetween,Alignment.CenterVertically) {
            Text(text = formatDay(weather.dt),Modifier.padding(3.dp))
            if (weather.weather[0].main == "Thunderstorm")
            {
                Image(painter = painterResource(id = R.drawable.a11d), contentDescription = "")
            }
           else if (weather.weather[0].main == "Drizzle")
            {
                Image(painter = painterResource(id = R.drawable.a09d), contentDescription = "")
            }
            else if (weather.weather[0].main == "Rain")
            {
                Image(painter = painterResource(id = R.drawable.a09d), contentDescription = "")
            }
            else if (weather.weather[0].main == "Rain"&&weather.weather[0].description == "freezing rain")
            {
                Image(painter = painterResource(id = R.drawable.a13d), contentDescription = "")
            }
            else if (weather.weather[0].main == "Snow")
            {
                Image(painter = painterResource(id = R.drawable.a13d), contentDescription = "")
            }
            else  if (weather.weather[0].main == "Mist")
            {
                Image(painter = painterResource(id = R.drawable.a50d), contentDescription ="" )
            }
            else if (weather.weather[0].main == "Smoke")
            {
                Image(painter = painterResource(id = R.drawable.a50d), contentDescription ="" )
            }
            else if (weather.weather[0].main == "Haze")
            {
                Image(painter = painterResource(id = R.drawable.a50d), contentDescription = "")
            }
            else if (weather.weather[0].main == "Dust")
            {
                Image(painter = painterResource(id = R.drawable.a50d), contentDescription ="" )
            }
            else if (weather.weather[0].main == "Fog")
            {
                Image(painter = painterResource(id = R.drawable.a50d), contentDescription = "")
            }
            else if (weather.weather[0].main == "Sand")
            {
                Image(painter = painterResource(id = R.drawable.a50d), contentDescription ="" )
            }
            else if (weather.weather[0].main == "Ash")
            {
                Image(painter = painterResource(id = R.drawable.a50d), contentDescription = "")
            }
            else if (weather.weather[0].main == "Squall")
            {
                Image(painter = painterResource(id = R.drawable.a50d), contentDescription = "")
            }
            else if (weather.weather[0].main == "Tornado")
            {
                Image(painter = painterResource(id = R.drawable.a50d), contentDescription ="" )
            }
            else if (weather.weather[0].main == "Clear")
            {
                Image(painter = painterResource(id = R.drawable.a01d), contentDescription = "")
            }
            else if (weather.weather[0].main == "Clouds"&&weather.weather[0].description == "few clouds")
            {
                Image(painter = painterResource(id = R.drawable.a02d), contentDescription ="" )
            }
            else if (weather.weather[0].main == "Clouds"&&weather.weather[0].description == "scattered clouds")
            {
                Image(painter = painterResource(id = R.drawable.a03d), contentDescription = "")
            }
            else if (weather.weather[0].main == "Clouds")
            {
                Image(painter = painterResource(id = R.drawable.a04d), contentDescription = "")
            }
            Text(
                text = weather.weather[0].main,
                Modifier
                    .background(color = Color.Yellow)
                    .border(shape = RoundedCornerShape(23.dp), width = 1.dp, color = Color.Yellow),
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(text = "${formatDecimals(formatTemp(weather.temp.max))}ºC",
                style = MaterialTheme.typography.bodyMedium,
            color = Color.Blue)

            Text(text = "${formatDecimals(formatTemp(weather.temp.min))}ºC",
                style = MaterialTheme.typography.bodyMedium,
            color = Color.DarkGray,
            modifier = Modifier.padding(3.dp))
        }
    }
}
