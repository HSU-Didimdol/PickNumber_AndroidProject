package com.example.data.repository

import com.example.data.api.Direction5Api
import com.example.data.di.annotation.IoDispatcher
import com.example.domain.repository.Directions5Repository
import kotlinx.coroutines.CoroutineDispatcher
import java.lang.Exception
import javax.inject.Inject

class Directions5RepositoryImpl @Inject constructor(
    private val direction5Api: Direction5Api,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : Directions5Repository {
    override suspend fun getBanksDistance(
        start: String,
        goal: String
    ): Int {
        val response = direction5Api.getDistance(start = start, goal = goal)
        if (response.data.isSuccessful) {
            return response.data.body()!!.routeDto.traoptimalDto[0].summaryDto.distance
        } else {
            throw Exception(response.data.message())
        }

    }
}