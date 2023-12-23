package com.example.weatherforecastapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherforecastapp.screens.MainScreen
import com.example.weatherforecastapp.screens.SplashScreen

@Composable
fun WeatherHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.name){

        composable(route = Screens.SplashScreen.name){

                    SplashScreen(host = navController)
        }

        composable(route = Screens.MainScreen.name){
            MainScreen(navController = navController)
        }
    }
}