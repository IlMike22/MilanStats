package com.example.milanstats.overview.domain.model

data class Team(
    val id: Int,
    val code: String,
    val name: String,
    val logo: String,
    val country: String,
    val founded: Int,
    val isNationalTeam: Boolean
)
