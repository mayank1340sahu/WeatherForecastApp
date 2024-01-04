package com.example.weatherforecastapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Unit(
@PrimaryKey
@ColumnInfo(name = "unit")
val unit : String
)