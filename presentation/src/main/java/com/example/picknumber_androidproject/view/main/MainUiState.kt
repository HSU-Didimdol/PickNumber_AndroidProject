package com.example.picknumber_androidproject.view.main

import androidx.annotation.StringRes
import com.example.domain.model.Bank

sealed class MainUiState {
    object Uninitialized : MainUiState()
    object Loading : MainUiState()

    data class Success(
        val BankList: MutableList<Bank>,
    ) : MainUiState()

    data class Error(
        @StringRes val id: Int
    ) : MainUiState()
}