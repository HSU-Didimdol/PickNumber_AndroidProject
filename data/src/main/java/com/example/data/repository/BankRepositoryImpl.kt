package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.api.BankApi
import com.example.data.api.Direction5Api
import com.example.data.paging.BankPagingSource
import com.example.data.paging.BankPagingSource.Companion.PAGE_SIZE
import com.example.domain.model.Bank
import com.example.domain.repository.BankRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BankRepositoryImpl @Inject constructor(
    private val bankApi: BankApi,
    private val direction5Api: Direction5Api,
) : BankRepository {
    override fun getAllBanks(): Flow<PagingData<Bank>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { BankPagingSource(api = bankApi) }
        ).flow
    }
}