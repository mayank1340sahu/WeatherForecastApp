package com.example.weatherforecastapp.network

import com.example.weatherforecastapp.Constant
import com.example.weatherforecastapp.Constant2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}