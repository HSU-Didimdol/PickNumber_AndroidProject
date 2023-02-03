package com.example.picknumber_androidproject.view.main

import android.location.LocationManager
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private lateinit var locationManager: LocationManager

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    fun getCurrentMyLocation() {

    }

    fun updateMarker(){

    }
}