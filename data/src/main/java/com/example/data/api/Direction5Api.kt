package com.example.data.api

import com.example.data.model.ResponseBody
import com.example.data.model.directions5.DirectionsDto
import com.example.data.url.Key
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Direction5Api {
    @GET("v1/driving")
    fun getDistance(
        @Header("X-NCP-APIGW-API-KEY-ID") clientID: String = Key.clientID,
        @Header("X-NCP-APIGW-API-KEY") clientSecret: String = Key.clientSecret,
        @Query("start", encoded = true) start: String,
        @Query("goal", encoded = true) goal: String,
    ): ResponseBody<Response<DirectionsDto>>
}