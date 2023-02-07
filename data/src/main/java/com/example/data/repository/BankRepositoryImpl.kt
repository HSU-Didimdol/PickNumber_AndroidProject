package com.example.data.repository

import com.example.data.api.BankApi
import com.example.data.extension.errorMessage
import com.example.data.mapper.toEntity
import com.example.domain.model.BankList
import com.example.domain.repository.BankRepository
import java.lang.Exception
import javax.inject.Inject

class BankRepositoryImpl @Inject constructor(
    private val bankApi: BankApi,
) : BankRepository {
    override suspend fun getAllBanks(
        start: String
    ): BankList {
        val response = bankApi.getBankList()
        if (response.isSuccessful) {
            return response.body()!!.data.toEntity()
        } else {
            throw Exception(response.errorMessage)
        }
    }
}