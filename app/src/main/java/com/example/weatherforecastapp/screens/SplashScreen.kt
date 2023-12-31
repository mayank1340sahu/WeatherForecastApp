package com.example.weatherforecastapp.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.navigation.Screens
import com.example.weatherforecastapp.view.WeatherViewModel

@Composable
fun SplashScreen(host : NavController,
                 viewModel: WeatherViewModel = hiltViewModel()) {
    val scale = remember{ androidx.compose.animation.core.Animatable(0f) }
    val default = viewModel.cityState.value

    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(800,
                delayMillis = 300,
            easing = {OvershootInterpolator(8f)
                .getInterpolation(it)
            }))
        host.navigate(Screens.MainScreen.name+"/$default")
    }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier
                .height(300.dp)
                .width(300.dp)
                .scale(scale.value)
                .border(width = 3.dp, shape = CircleShape, color = Color.Black)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(painterResource(id = R.drawable.find),
                    contentDescription ="hiding sun", 
                    contentScale = ContentScale.Fit)
                
                Spacer(modifier = Modifier.height(5.dp))

                Text(text = "Find The Sun ?", fontSize = 16.sp,color = Color.LightGray)
            }
        }
    }
}
