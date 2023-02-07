package com.example.domain.repository

import com.example.domain.model.BankList

interface BankRepository {
    suspend fun getAllBanks(start: String): BankList
}