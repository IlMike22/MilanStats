package com.example.milanstats.overview.presentation

data class OverviewState(
    val countries: String = "",
    val error: String? = null,
    val isLoading: Boolean = false
)
