package com.example.data.Model.Directions5

data class DirectionsDto(
    val code: Int,
    val currentDateTime: String,
    val message: String,
    val routeDto: RouteDto
)