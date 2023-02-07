package com.example.domain.repository

import com.example.domain.model.Bank

interface BankRepository {
    suspend fun getAllBanks(start: String): List<Bank>
}