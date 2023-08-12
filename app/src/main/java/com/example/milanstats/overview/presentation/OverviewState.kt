package com.example.milanstats.overview.presentation

import com.example.milanstats.overview.domain.model.Country
import com.example.milanstats.overview.domain.model.TeamStatistic

data class OverviewState(
    val teamCountry: Country = Country.EMPTY,
    val teamName: String = "",
    val teamLogo: String = "",
    val teamStatistic: TeamStatistic = TeamStatistic.EMPTY,
    val currentTable: String = "", // TODO MIC do this next time
    val error: String? = null,
    val isLoading: Boolean = false,
    val searchText: String = "",
    val greetingsText: String = ""
)
