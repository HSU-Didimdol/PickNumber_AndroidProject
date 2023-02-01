package com.example.data.Model.Directions5

data class TraoptimalDto(
    val guideDto: List<GuideDto>,
    val path: List<List<Double>>,
    val sectionDto: List<SectionDto>,
    val summaryDto: SummaryDto
)