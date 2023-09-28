package com.example.milanstats.goalscorer.data.dto

data class GoalScorerDto(
    val errors: List<Any>,
    val `get`: String,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int
)