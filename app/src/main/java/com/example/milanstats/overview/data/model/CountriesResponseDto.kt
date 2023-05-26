package com.example.milanstats.overview.data.model

data class CountriesResponseDto(
    val errors: List<Any>,
    val get: String,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int
)