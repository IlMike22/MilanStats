package com.example.milanstats.overview.presentation

import com.example.milanstats.overview.domain.model.Country
import com.example.milanstats.overview.domain.model.League
import com.example.milanstats.overview.domain.model.Team
import com.example.milanstats.overview.domain.model.TeamStatistic

data class OverviewState(
    val countries: List<Country> = emptyList(),
    val leagues: List<League> = emptyList(),
    val teams: List<Team> = emptyList(),
    val teamStatistic: TeamStatistic = TeamStatistic.EMPTY,
    val error: String? = null,
    val isLoading: Boolean = false
)
