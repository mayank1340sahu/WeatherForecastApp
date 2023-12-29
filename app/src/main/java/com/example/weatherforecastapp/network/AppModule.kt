package com.example.weatherforecastapp.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.weatherforecastapp.Constant
import com.example.weatherforecastapp.Constant2
import com.example.weatherforecastapp.room.WeatherDao
import com.example.weatherforecastapp.room.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideWeatherApi() : WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constant.URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
    @Provides
    @Singleton
    fun provideNewWeatherApi() : NewWeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constant2.URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewWeatherApi::class.java)
    }
    @Provides
    @Singleton
    fun provideWeatherDao(database: WeatherDatabase) :WeatherDao{
        return database.gerWeather()
    }
    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context) : WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_database"
        )
            .fallbackToDestructiveMigration()
            .build()
}
}
