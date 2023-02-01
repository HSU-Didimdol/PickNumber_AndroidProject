package com.example.data

import retrofit2.Call
import retrofit2.http.GET

interface BankApi {
    @GET("/v3/16518b01-5893-4a01-ba1d-5f086f0cfdfa")
    fun getBankList(): Call<BankDto>
}