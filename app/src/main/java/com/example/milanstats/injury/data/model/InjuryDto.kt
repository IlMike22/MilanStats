package com.example.milanstats.injury.data.model

data class InjuryDto(
    val errors: List<Any>,
    val `get`: String,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int
)