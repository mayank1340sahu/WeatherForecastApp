package com.example.weatherforecastapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherforecastapp.screens.AboutScreen
import com.example.weatherforecastapp.screens.FavoriteScreen
import com.example.weatherforecastapp.screens.MainScreen
import com.example.weatherforecastapp.screens.SearchScreen
import com.example.weatherforecastapp.screens.SettingScreen
import com.example.weatherforecastapp.screens.SplashScreen
import com.example.weatherforecastapp.view.WeatherViewModel

@Composable
fun WeatherHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.name){

        composable(route = Screens.SplashScreen.name){

                    SplashScreen(host = navController)
        }
        val route =  Screens.MainScreen.name
        composable(route = "$route/{city}",
            arguments = listOf(navArgument("city"){type = NavType.StringType})) {
            it.arguments?.getString("city").let { rt ->
                val viewModel = hiltViewModel<WeatherViewModel>()
                if (rt != null) {
                    MainScreen(navController = navController, viewModel = viewModel,city = rt)
                }
            }
        }
        composable(route = Screens.SearchScreen.name){
            val viewModel = hiltViewModel<WeatherViewModel>()
           SearchScreen(navController = navController,viewModel = viewModel)
        }
        composable(route = Screens.FavouriteScreen.name){
            FavoriteScreen(navController = navController)
        }
        composable(route = Screens.AboutScreen.name){
            AboutScreen(navController = navController)
        }
        composable(route = Screens.SettingScreen.name){
            SettingScreen(navController = navController)
        }
    }
}


