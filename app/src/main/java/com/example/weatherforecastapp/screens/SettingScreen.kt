package com.example.weatherforecastapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weatherforecastapp.room.Unit
import com.example.weatherforecastapp.room.UnitViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavHostController) {
    val unitViewModel = hiltViewModel<UnitViewModel>()
    val choices = listOf("imperial","metric")
    val choiceState = rememberSaveable {
        mutableStateOf("")
    }
    val unitToggleState = rememberSaveable {
        mutableStateOf(false)
    }
    val units = unitViewModel.unitList.collectAsState().value
    if (units.isEmpty()){
        unitViewModel.insertUnit(Unit(choices[1]))
    }
    if (units.isNotEmpty()){
        unitToggleState.value = units[0].unit != "imperial"
    }

    Scaffold (topBar = { WeatherAppBar(
        title = "Settings", icon = Icons.Default.ArrowBack,
        isMainScreen = false,
        navController = navController
    ){navController.popBackStack()}}){
        Column(
            Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           Text(text = "Change Unit of Measurement", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(6.dp))

            IconToggleButton(checked = !unitToggleState.value, onCheckedChange = { toggle ->
                unitToggleState.value = !toggle
                if (unitToggleState.value){
                    choiceState.value = choices[1]
                }
                else{
                    choiceState.value = choices[0]
                }
            },Modifier.height(70.dp)
                .width(200.dp)
                   ) {
                Column( Modifier
                    .background(Color(0xFFAA00FF))
                    .clip(RoundedCornerShape(23.dp))){
                    Text(
                        text = if (unitToggleState.value) {
                            "Celsius C"
                        } else {
                            "Fahrenheit F"
                        }, color = Color.White.copy(alpha = .9f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                }

            }
            Button(onClick = {
                unitViewModel.deleteUnit(
                    unit = units[0]
                )
                unitViewModel.insertUnit(Unit(choiceState.value))
            }) {
                Text(text = "Save")
            }
        }
    }
}