package com.example.data.di

import com.example.data.repository.BankRepositoryImpl
import com.example.data.repository.Directions5RepositoryImpl
import com.example.domain.repository.BankRepository
import com.example.domain.repository.Directions5Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Singleton
    @Binds
    abstract fun bindBankRepository(
        bankRepositoryImpl: BankRepositoryImpl
    ): BankRepository

    @Singleton
    @Binds
    abstract fun bindDirections5Repository(
        directions5RepositoryImpl: Directions5RepositoryImpl
    ): Directions5Repository

}