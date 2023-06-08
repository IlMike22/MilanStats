package com.example.milanstats.overview.domain.repository

import com.example.milanstats.overview.domain.model.Country
import com.example.milanstats.overview.domain.model.League
import com.example.milanstats.overview.domain.model.Team
import com.example.milanstats.overview.domain.model.TeamStatistic

interface IOverviewRepository {
    suspend fun getCountries(countryName: String): List<Country>
    suspend fun getLeagues(countryCode: String): List<League>
    suspend fun getTeamByName(name: String): List<Team>
    suspend fun getTeamStatisticsBySeason(league: Int, teamCode: Int, season: Int): TeamStatistic
}