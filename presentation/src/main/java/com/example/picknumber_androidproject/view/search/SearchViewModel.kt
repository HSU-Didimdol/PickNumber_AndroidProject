package com.example.picknumber_androidproject.view.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = viewModelScope.launch { searchBank("") }

    fun searchBank(name: String) {
        job?.cancel()
        job = viewModelScope.launch {

        }
    }


}