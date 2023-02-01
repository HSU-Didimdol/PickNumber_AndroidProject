package com.example.data.Model.Directions5

import com.example.data.Model.Directions5.Goal
import com.example.data.Model.Directions5.Start

data class Summary(
    val bbox: List<List<Double>>,
    val departureTime: String,
    val distance: Int,
    val duration: Int,
    val etaServiceType: Int,
    val fuelPrice: Int,
    val goal: Goal,
    val start: Start,
    val taxiFare: Int,
    val tollFare: Int
)