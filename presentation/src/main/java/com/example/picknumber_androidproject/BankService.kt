package com.example.picknumber_androidproject

import retrofit2.Call
import retrofit2.http.GET

interface BankService {
    @GET("/v3/bd726118-990e-4614-8d27-7653146aae79")
    fun getBankList(): Call<BankDto>
}