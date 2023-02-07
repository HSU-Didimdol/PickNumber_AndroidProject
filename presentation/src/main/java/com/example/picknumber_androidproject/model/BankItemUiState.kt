package com.example.picknumber_androidproject.model

import java.io.Serializable

data class BankItemUiState(
    val name: String,
    val address: String,
    val distance: Int,
    val longitude: Double,
    val latitude: Double,
) : Serializable