package com.example.data.api

import com.example.data.Model.Directions5.DirectionModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Direction5Api {
    @GET("v1/driving")
    fun getDistance(
        @Header("X-NCP-APIGW-API-KEY-ID") clientID: String,
        @Header("X-NCP-APIGW-API-KEY") clientSecret: String,
        @Query("start", encoded = true) start: String,
        @Query("goal", encoded = true) goal: String,
    ): Call<DirectionModel>
}