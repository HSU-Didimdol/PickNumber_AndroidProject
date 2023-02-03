package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.Bank
import kotlinx.coroutines.flow.Flow

interface BankRepository {
    fun getAllBanks(): Flow<PagingData<Bank>>
}