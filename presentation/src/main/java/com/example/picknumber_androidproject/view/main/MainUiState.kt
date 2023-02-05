package com.example.picknumber_androidproject.view.main

import androidx.paging.PagingData

data class MainUiState(
    //val bankPagingData: PagingData<> = PagingData.empty()
    private val onUpdate: (MainUiState) -> Unit
) {
    fun update(function: (MainUiState) -> MainUiState) {
        onUpdate(function(this))
    }

}

