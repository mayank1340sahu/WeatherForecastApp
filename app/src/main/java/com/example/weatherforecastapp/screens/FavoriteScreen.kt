package com.example.weatherforecastapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherforecastapp.navigation.Screens
import com.example.weatherforecastapp.room.Favorite
import com.example.weatherforecastapp.room.RoomViewModel
import com.example.weatherforecastapp.view.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    navController: NavHostController,
    favoriteViewModel: RoomViewModel = hiltViewModel(),
    weatherViewModel: WeatherViewModel = hiltViewModel(),
) {
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val favorite = favoriteViewModel.favList.collectAsState()
    Scaffold (topBar = { WeatherAppBar(
        title = "Favorite Screen", icon = Icons.Default.ArrowBack,
        isMainScreen = false,
        navController = navController
    )
    {navController.navigate(Screens.MainScreen.name+"/${weatherViewModel.cityState.value}")}}){
        Column(
            Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
          LazyColumn {
              items(favorite.value) { fav ->
                  Card(
                      Modifier
                          .height(100.dp)
                          .fillMaxWidth()
                          .padding(6.dp),
                      elevation = CardDefaults.cardElevation(6.dp),
                      colors = CardDefaults.cardColors(Color(0xFFAA00FF))
                  ) {
                      Row(
                          Modifier.fillMaxSize(),
                          verticalAlignment = Alignment.CenterVertically,
                          horizontalArrangement = Arrangement.SpaceBetween
                      ) {
                          Text(text = fav.city,
                              color = Color.White,
                              fontSize = 25.sp,
                              fontWeight = FontWeight.Medium)
                          Text(text = fav.country, color = Color.White,
                              fontSize = 25.sp,
                              fontWeight = FontWeight.Medium)
                          Icon(imageVector = Icons.Default.Delete,
                              contentDescription = "Delete",
                              tint = MaterialTheme.colorScheme.onBackground,
                              modifier = Modifier.clickable {
                                  favoriteViewModel.deleteFavorite(Favorite(fav.city, fav.country))
                                  navController.navigate(Screens.FavouriteScreen.name)

                                  // Get the previous entry index
                                  val previousEntryIndex = navController.backQueue.indexOf(currentBackStackEntry) - 1

                                  // Check if there is a valid previous entry
                                  if (previousEntryIndex >= 0) {
                                      // Get the destination ID of the previous entry
                                      val previousDestinationId = navController.backQueue[previousEntryIndex].destination.id

                                      // Pop back stack up to the previous entry
                                      currentBackStackEntry?.destination?.route?.let { it1 ->
                                          navController.navigate(route = it1) {
                                              popUpTo(previousDestinationId) {
                                                  inclusive = true
                                              }
                                          }
                                      }
                                  }
                              })
                      }
                  }
              }
          }
        }
    }
}