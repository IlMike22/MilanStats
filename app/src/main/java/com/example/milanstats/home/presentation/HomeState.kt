package com.example.milanstats.home.presentation

import com.example.milanstats.home.domain.model.Country
import com.example.milanstats.home.domain.model.TeamStatistic

data class HomeState(
    val teamCountry: Country = Country.EMPTY,
    val teamName: String = "",
    val teamLogo: String = "",
    val teamStatistic: TeamStatistic = TeamStatistic.EMPTY,
    val currentTable: String = "", // TODO MIC do this next time
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val searchText: String = "",
    val greetingsText: String = ""
)
