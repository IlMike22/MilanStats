package com.example.milanstats.overview.presentation

import com.example.milanstats.overview.domain.model.Country
import com.example.milanstats.overview.domain.model.League

data class OverviewState(
    val countries: List<Country> = emptyList(),
    val leagues: List<League> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false
)
