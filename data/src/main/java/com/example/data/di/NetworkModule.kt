package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.api.BankApi
import com.example.data.api.Direction5Api
import com.example.data.di.annotation.BankRetrofitInstance
import com.example.data.di.annotation.Directions5RetrofitInstance
import com.example.data.url.Url
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()

        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @BankRetrofitInstance
    @Provides
    @Singleton
    fun provideBankRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Url.MOCK_BANK_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Directions5RetrofitInstance
    @Provides
    @Singleton
    fun provideDirections5Retrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Url.DIRECTION5_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideBankApiService(@BankRetrofitInstance retrofit: Retrofit): BankApi {
        return retrofit.create(BankApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDirection5ApiService(@Directions5RetrofitInstance retrofit: Retrofit): Direction5Api {
        return retrofit.create(Direction5Api::class.java)
    }

}