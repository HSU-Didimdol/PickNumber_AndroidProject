package com.example.data.di

import com.example.data.api.BankApi
import com.example.data.api.Direction5Api
import com.example.data.url.Url
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Url.MOCK_BANK_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBankApiService(retrofit: Retrofit): BankApi {
        return retrofit.create(BankApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDirection5ApiService(retrofit: Retrofit): Direction5Api {
        return retrofit.create(Direction5Api::class.java)
    }

}