package com.example.weatherforecastapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("Select * from favorite")
     fun listFavorite():Flow<List<Favorite>>

    @Query("Select * from favorite where city = :city")
    suspend fun getFavoriteByCity(city: String):Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Query("Delete from Favorite")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(favorite: Favorite)

    // Unit table

    @Query("Select * from Unit")
    fun getUnits():Flow<List<Unit>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(unit: Unit)

    @Query("Delete from Unit")
    suspend fun deleteAllUnit()

    @Delete
    suspend fun deleteUnit(unit: Unit)

}