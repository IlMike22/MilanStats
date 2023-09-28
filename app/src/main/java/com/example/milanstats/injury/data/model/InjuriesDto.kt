package com.example.milanstats.injury.data.model

data class InjuriesDto(
    val errors: Errors,
    val `get`: String,
    val parameters: Parameters,
    val response: List<InjuriesResponse>,
    val results: Int
)