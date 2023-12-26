package com.example.weatherforecastapp.screens.widgt

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun WeatherImage(string: String) {
    Image(painter = rememberImagePainter(data = string),
        contentDescription ="Image", modifier = Modifier.size(80.dp))
}
