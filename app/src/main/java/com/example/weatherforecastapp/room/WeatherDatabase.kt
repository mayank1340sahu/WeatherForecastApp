package com.example.weatherforecastapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherforecastapp.room.Unit

@Database([Favorite::class,Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase(){
    abstract fun gerWeather() : WeatherDao
}