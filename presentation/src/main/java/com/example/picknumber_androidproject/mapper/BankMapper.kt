package com.example.picknumber_androidproject.mapper

import com.example.domain.model.Bank
import com.example.domain.model.BankList
import com.example.picknumber_androidproject.model.BankItemUiState
import com.example.picknumber_androidproject.model.BankUiState

fun Bank.toUiState() = BankItemUiState(
    name = name,
    address = address,
    distance = distance,
    latitude = latitude,
    longitude = longitude
)

fun BankList.toUiState() = BankUiState(
    banks = data.map {
        it.toUiState()
    }
)