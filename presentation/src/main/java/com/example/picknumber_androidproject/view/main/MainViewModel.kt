package com.example.picknumber_androidproject.view.main

import android.location.LocationManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.domain.usecase.GetAllBankUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllBankUseCase: GetAllBankUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState(onUpdate = ::update))
    val uiState: StateFlow<MainUiState> get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getAllBankUseCase()
                .cachedIn(viewModelScope)
                .map { it }
                .collectLatest {
                    _uiState.update {
                        it.copy()
                    }
                }
        }
    }

    private fun update(uiState: MainUiState) {
        _uiState.value = uiState
    }

}