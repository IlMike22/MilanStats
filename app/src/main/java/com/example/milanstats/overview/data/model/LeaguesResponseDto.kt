package com.example.milanstats.overview.data.model

data class LeaguesResponseDto(
    val errors: List<Any>,
    val `get`: String,
    val paging: PagingX,
    val parameters: ParametersX,
    val response: List<ResponseX>,
    val results: Int
)