package com.example.domain.usecase

import com.example.domain.repository.BankRepository
import javax.inject.Inject

class GetBankListUseCase @Inject constructor(
    private val repository: BankRepository
) {
    suspend operator fun invoke(start: String) = repository.getAllBanks(start)
}
