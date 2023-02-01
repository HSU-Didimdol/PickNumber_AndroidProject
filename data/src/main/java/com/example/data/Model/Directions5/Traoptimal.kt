package com.example.data.Model.Directions5

import com.example.data.Model.Directions5.Guide
import com.example.data.Model.Directions5.Section
import com.example.data.Model.Directions5.Summary

data class Traoptimal(
    val guide: List<Guide>,
    val path: List<List<Double>>,
    val section: List<Section>,
    val summary: Summary
)