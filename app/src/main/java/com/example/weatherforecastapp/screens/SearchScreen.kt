package com.example.weatherforecastapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherforecastapp.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(topBar = { WeatherAppBar(navController =navController,
                            title = "Search", icon = Icons.Default.Search, isMainScreen = false)}) {
                SearchBar(paddingValues = it, onSearch = { city ->
                        navController.navigate(Screens.MainScreen.name+"/$city")
                })
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(paddingValues: PaddingValues,
              onSearch : (String) -> Unit
) {
    Column() {
        val searchQueryState = rememberSaveable {
            mutableStateOf("")
        }
        val keyBoardController = LocalSoftwareKeyboardController.current
        val valid = remember (searchQueryState.value){
            searchQueryState.value.trim().isNotBlank()
        }
        CommonTextField(paddingValues = paddingValues,
            valueState = searchQueryState,
            placeHolder = "Raipur",
        onAction = KeyboardActions{
            if (!valid) return@KeyboardActions
            onSearch(searchQueryState.value.trim())
            searchQueryState.value = ""
            keyBoardController?.hide()
        })
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(paddingValues: PaddingValues,
                    keyboardType: KeyboardType = KeyboardType.Text,
                    valueState:MutableState<String>,
                    placeHolder:String,
                    imeAction: ImeAction = ImeAction.Next,
                    onAction: KeyboardActions = KeyboardActions.Default) {
    TextField(value = valueState.value,
        onValueChange = {valueState.value = it},
        modifier = Modifier.padding(paddingValues = paddingValues),
        maxLines = 1,
        label = { Text(text = placeHolder)},
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp)
    )
}