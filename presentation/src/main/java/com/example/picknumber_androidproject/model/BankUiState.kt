package com.example.picknumber_androidproject.model

import java.io.Serializable

data class BankUiState(
    val banks: List<BankItemUiState> = emptyList()
) : Serializable