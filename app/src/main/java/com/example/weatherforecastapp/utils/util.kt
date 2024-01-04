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
fun formatDay(dat : Int): String {
    val pattern = SimpleDateFormat("EEE")
    val date = Date(dat.toLong()*1000)
    return pattern.format(date)
}
fun formatTempF(K:Double): Double {
return ((9/5)*(K - 273.15))+32
}
fun formatTempC(K:Double): Double {
    return K-273.15
}

fun formatTime(dat: Int): String {
    val params = SimpleDateFormat("hh:mm a")
    val date = Date(dat.toLong()*1000)
    return params.format(date)
}