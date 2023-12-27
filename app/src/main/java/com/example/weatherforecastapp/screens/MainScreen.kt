package com.example.weatherforecastapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforecastapp.model.Weather
import com.example.weatherforecastapp.navigation.Screens
import com.example.weatherforecastapp.newModel.NewWeather
import com.example.weatherforecastapp.screens.widgt.SunState
import com.example.weatherforecastapp.screens.widgt.WeatherDropDownMenu
import com.example.weatherforecastapp.screens.widgt.WeatherHumidity
import com.example.weatherforecastapp.screens.widgt.WeatherImage
import com.example.weatherforecastapp.screens.widgt.WeekWeather
import com.example.weatherforecastapp.utils.formatDate
import com.example.weatherforecastapp.utils.formatDecimals
import com.example.weatherforecastapp.view.DataOrException
import com.example.weatherforecastapp.view.WeatherViewModel

@Composable
fun MainScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    navController: NavController,
    city: String,
) {
    Column {
        val weatherData =
            produceState<DataOrException<Weather,Boolean,Exception>>(initialValue = DataOrException(loading = true))
            {
                value = viewModel.getWeatherData(city)
            }.value

        val newWeatherData =
            produceState<DataOrException<NewWeather,Boolean,Exception>>(initialValue = DataOrException(loading = true))
            {
                value = viewModel.getNewWeatherData(city)
            }.value

        if (newWeatherData.loading == true && weatherData.loading == true){
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){ CircularProgressIndicator() }
        }
        if(newWeatherData.data == null && weatherData.data == null){
            Column() {
                Text(text = "Not Found!")
                Text(text = "Please check your internet")
                Text(text = "if you are searching then check your spelling")
            }
        }
        else if(newWeatherData.data != null && weatherData.data != null)
        {
            MainScaffold(weatherData.data!!, navController = navController,newWeatherData.data!!)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    weather: Weather,
    navController: NavController,
    newWeather: NewWeather,
){
    Scaffold(topBar = {WeatherAppBar(navController = navController,
        elevation = 4.dp,
    title = weather.city.name + ",${newWeather.location.region} ${newWeather.location.country}"
    )
    }) {
        MainContent(paddingValues = it,weather,newWeather)
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
    val dialog = remember{
        mutableStateOf(false)
    }
    if (dialog.value){
        WeatherDropDownMenu(dialog = dialog,navController = navController)
    }
   Card(elevation = CardDefaults.cardElevation(elevation),
   modifier = Modifier.padding(6.dp)) {

        TopAppBar(
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.White),
            title = { Text(text = title) },
            actions = {
                if (isMainScreen) {
                    IconButton(onClick = { navController.navigate(Screens.SearchScreen.name) }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search button"
                        )
                    }
                    IconButton(onClick = { dialog.value = true }) {
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
fun MainContent(
    paddingValues: PaddingValues,
    weather: Weather,
    newWeather: NewWeather,
) {
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
               WeatherImage(
                   "https://${newWeather.current.condition.icon}"
               )
               Text(text = formatDecimals(newWeather.current.temp_c)+"ºC",
                   style = MaterialTheme.typography.displayMedium)
               Text(text = newWeather.current.condition.text, fontStyle = FontStyle.Italic)
           }
       }
       WeatherHumidity(weather = weather)
       Divider()
       SunState(weather = weather)
        WeekWeather(weather = weather)
   }
}




