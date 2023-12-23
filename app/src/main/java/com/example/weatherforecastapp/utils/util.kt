package com.example.weatherforecastapp.utils

import java.text.SimpleDateFormat
import java.util.Date

fun formatDate(dat : Int): String {
    val pattern = SimpleDateFormat("EEE, MMM d")
    val date = Date(dat.toLong()*1000)
    return pattern.format(date)
}

fun formatDecimals(num: Double): String {
    return "%.0f".format(num)
}