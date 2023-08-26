package com.example.milanstats.home.data.model.teams

data class TeamsDto(
    val errors: List<Any>,
    val get: String,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<TeamsResponse>,
    val results: Int
)