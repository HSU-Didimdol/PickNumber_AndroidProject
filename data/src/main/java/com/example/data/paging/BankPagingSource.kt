package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.api.BankApi
import com.example.data.extension.errorMessage
import com.example.data.mapper.toEntity
import com.example.domain.model.Bank
import java.lang.Exception
import javax.inject.Inject

class BankPagingSource @Inject constructor(
    private val api: BankApi
) : PagingSource<Int, Bank>() {
    companion object {
        const val PAGE_SIZE = 30
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Bank> {
        val currentPage = params.key ?: 0
        val nextPage = minOf(currentPage + PAGE_SIZE, 500)
        return try {
            val response = api.getBankList()
            if (response.isSuccessful) {
                val banks = response.body()!!.data.items.map { it.toEntity() }
                val isEnd = banks.isEmpty()
                LoadResult.Page(
                    data = banks,
                    prevKey = null,
                    nextKey = if (nextPage == 500) null else nextPage
                )
            } else {
                throw Exception(response.errorMessage)
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Bank>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

