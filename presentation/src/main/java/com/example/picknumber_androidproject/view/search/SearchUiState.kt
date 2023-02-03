package com.example.picknumber_androidproject.view.search

import androidx.paging.PagingData
import com.example.data.model.bank.BankDto

data class SearchUiState(
    val pagingData: PagingData<SearchItemUiState> = PagingData.empty()
)

data class SearchItemUiState(
    val name: String,
    val address: String,
    val distance: Int
)

fun BankDto.toSearchItemUiState() = SearchItemUiState(
    name = "$name 새마을금고 $divisionName",
    address = address,
    distance = distance
)