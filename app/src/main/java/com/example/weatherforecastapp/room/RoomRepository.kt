package com.example.weatherforecastapp.room

import javax.inject.Inject

class RoomRepository @Inject constructor(private val weatherDao: WeatherDao ) {

     fun getAllFavorite()  = weatherDao.listFavorite()

    suspend fun getFavoriteByCity(city:String) = weatherDao.getFavoriteByCity(city)

    suspend fun addFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)

    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite = favorite)

    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.delete(favorite = favorite)

    suspend fun deleteAll() = weatherDao.deleteAll()
    //Unit Repo

    fun getUnits() = weatherDao.getUnits()

    suspend fun addUnit(unit: Unit) = weatherDao.insertUnit(unit)

    suspend fun updateUnit(unit: Unit) = weatherDao.updateFavorite(unit)

    suspend fun deleteUnit(unit: Unit) = weatherDao.deleteUnit(unit)

    suspend fun deleteAllUnit() = weatherDao.deleteAllUnit()

}