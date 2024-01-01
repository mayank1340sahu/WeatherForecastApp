package com.example.weatherforecastapp.screens.widgt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherforecastapp.navigation.Screens

@Composable
fun WeatherDropDownMenu(navController: NavController, dialog: MutableState<Boolean>) {
    val items = listOf("favorites","about","setting")
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)) {

        DropdownMenu(expanded = dialog.value, onDismissRequest = { dialog.value = false }) {
            items.forEach(){string ->
                DropdownMenuItem(text = {
                Row() {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription =null)
                    Text(text = string)
                } } , onClick = { navController.navigate(
                    when(string){
                        "favorites" -> Screens.FavouriteScreen.name
                        "about" -> Screens.AboutScreen.name
                        else -> Screens.SettingScreen.name
                    })
                })
            }
        }
    }
}