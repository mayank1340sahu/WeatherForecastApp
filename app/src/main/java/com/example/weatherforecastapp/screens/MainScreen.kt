package com.example.weatherforecastapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.model.Weather
import com.example.weatherforecastapp.newModel.NewWeather
import com.example.weatherforecastapp.utils.formatDate
import com.example.weatherforecastapp.utils.formatDecimals
import com.example.weatherforecastapp.utils.formatTemp
import com.example.weatherforecastapp.utils.formatTime
import com.example.weatherforecastapp.view.DataOrException
import com.example.weatherforecastapp.view.WeatherViewModel

@Composable
fun MainScreen(viewModel: WeatherViewModel = hiltViewModel(),navController: NavController) {
    Column {
        val weatherData =
            produceState<DataOrException<Weather,Boolean,Exception>>(initialValue = DataOrException(loading = true))
            {
                value = viewModel.getWeatherData("Raipur")
            }.value

        if (weatherData.loading == true){
            CircularProgressIndicator()
        }
        else if(weatherData.data != null)
        {
            MainScaffold(weatherData.data!!, navController = navController)
        }
       
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(weather: Weather, navController: NavController){
    Scaffold(topBar = {WeatherAppBar(navController = navController,
        elevation = 4.dp,
    title = weather.city.name + ",${weather.city.country}"
    )
    }) {
        MainContent(paddingValues = it,weather)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen : Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked : () -> Unit = {} ,
    onButtonClicked : () -> Unit = {}
) {
   Card(elevation = CardDefaults.cardElevation(elevation),
   modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.White),
            title = { Text(text = title) },
            actions = {
                if (isMainScreen) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search button"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options"
                        )
                    }
                }
                else Box(Modifier)
            },
            navigationIcon = {
                if(icon != null){
                    IconButton(onClick = {onButtonClicked()}) {
                        Icon(imageVector = icon, contentDescription =null)
                    }
                }
            },
        )
    }
}


@Composable
fun MainContent(paddingValues: PaddingValues, weather: Weather) {
   Column(modifier = Modifier
       .padding(paddingValues)
       .fillMaxSize(),
       horizontalAlignment = Alignment.CenterHorizontally,
   verticalArrangement = Arrangement.Top) {

       Text(text = formatDate(weather.list[0].dt), style = MaterialTheme.typography.headlineSmall)

       Surface(modifier = Modifier.size(200.dp),
       shape = CircleShape,
       color = Color(0xFFFFc300)
       ) {

           Column(horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center){
               WeatherImage(weather = weather)
               Text(text = formatDecimals(formatTemp(weather.list[0].temp.day))+"ºC",
                   style = MaterialTheme.typography.displayMedium)
               Text(text = weather.list[0].weather[0].main, fontStyle = FontStyle.Italic)
           }
       }
       WeatherHumidity(weather = weather)
       Divider()
       SunState(weather = weather)
   }
}

@Composable
fun WeatherImage(weather: Weather) {
    val string = "https://openweathermap.org/img/wn/${weather.list[0].weather[0].icon}.png"
    Image(painter = rememberImagePainter(data = string),
        contentDescription ="Image", modifier = Modifier.size(80.dp))
}

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
            Text(text = "${ formatTime(weather.list[0].sunrise) } ", style = MaterialTheme.typography.bodyLarge)
        }
        Row() {
            Image( painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Humidity Icon",
                Modifier.size(20.dp))
            Text(text = "${formatTime( weather.list[0].sunset) } ", style = MaterialTheme.typography.bodyLarge)
        }
    }
}