package com.example.domain.repository

interface Directions5Repository {
    suspend fun getBanksDistance(
        start: String,
        goal: String
    ): Int

}