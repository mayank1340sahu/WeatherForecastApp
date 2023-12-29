package com.example.weatherforecastapp.room

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(private val repository: RoomRepository) : ViewModel() { 
     val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()
    init {
        viewModelScope.launch { 
            repository.getAllFavorite().distinctUntilChanged().collect(){
                if (it.isEmpty()){
                    Log.d("favoriteList", "empty fav ")
                }
                else{
                    _favList.value = it
                    Log.d("FavoriteList", "${it}: ")
                }
            }
        }
    }
    fun insertFavorite(favorite: Favorite) = viewModelScope.launch { repository.addFavorite(favorite) }
    fun updateFavorite(favorite: Favorite) = viewModelScope.launch { repository.updateFavorite(favorite) }
    fun deleteFavorite(favorite: Favorite) = viewModelScope.launch { repository.deleteFavorite(favorite) }
}