package com.example.milanstats.injuries.data.model

data class InjuriesDto(
    val errors: List<Any>,
    val `get`: String,
    val parameters: Parameters,
    val response: List<InjuriesResponse>,
    val results: Int
)