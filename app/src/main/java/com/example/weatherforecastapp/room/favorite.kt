package com.example.weatherforecastapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
 data class Favorite (
    @NotNull
@PrimaryKey
    @ColumnInfo(name = "City")
  var city : String,
    @ColumnInfo("Country")
  var country : String
  )