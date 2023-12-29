package com.example.weatherforecastapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database([Favorite::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase(){
    abstract fun gerWeather() : WeatherDao
}