package com.example.milanstats.overview.presentation

import com.example.milanstats.overview.domain.model.Country

data class OverviewState(
    val countries: List<Country> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false
)
