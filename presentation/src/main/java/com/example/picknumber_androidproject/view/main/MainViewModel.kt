package com.example.picknumber_androidproject.view.main

import androidx.lifecycle.*
import com.example.domain.usecase.GetBankListUseCase
import com.example.picknumber_androidproject.mapper.toUiState
import com.example.picknumber_androidproject.model.BankUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBankListUseCase: GetBankListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BankUiState())
    val uiState = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    init {
        fetchUserList()
    }

    private fun fetchUserList() = viewModelScope.launch {
        _uiState.update {
            it.copy(banks = getBankListUseCase("126.9050532,37.4652659").data.map { bank ->
                bank.toUiState()
            })
        }

    }
}


