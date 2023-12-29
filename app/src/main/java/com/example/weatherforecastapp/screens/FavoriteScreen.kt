package com.example.weatherforecastapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.room.Favorite
import com.example.weatherforecastapp.room.RoomViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(navController: NavHostController,
                   favoriteViewModel : RoomViewModel = hiltViewModel(),
) {
    val favorite = favoriteViewModel.favList.collectAsState()
    Scaffold (topBar = { WeatherAppBar(navController = navController,
        icon = Icons.Default.ArrowBack,
        isMainScreen = false){navController.popBackStack()}}){
        Column(
            Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
          LazyColumn(content = {
              items(favorite.value){fav ->
                  Row(
                      Modifier
                          .height(45.dp)
                          .fillMaxWidth()
                          .padding(6.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.SpaceBetween) {
                      Text(text = fav.city)
                      Text(text = fav.country)
                      Icon(imageVector = Icons.Default.Delete,
                          contentDescription = "Delete",
                          tint = MaterialTheme.colorScheme.primary,
                      modifier = Modifier.clickable {
                          favoriteViewModel.deleteFavorite(Favorite(fav.city,fav.country))
                      })
                  }
              }
          })
        }
    }
}