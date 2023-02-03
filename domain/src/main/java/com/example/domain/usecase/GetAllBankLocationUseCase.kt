package com.example.domain.usecase

import com.example.domain.repository.BankRepository
import javax.inject.Inject

class GetAllBankLocationUseCase @Inject constructor(
    private val repository: BankRepository
) {
    operator fun invoke() = repository.getAllBanks()
}