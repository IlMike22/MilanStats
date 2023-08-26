package com.example.milanstats.home.domain.repository

import com.example.milanstats.home.domain.model.Country
import com.example.milanstats.home.domain.model.League
import com.example.milanstats.home.domain.model.Team
import com.example.milanstats.home.domain.model.TeamStatistic

interface IHomeRepository {
    suspend fun getCountries(countryName: String): List<Country>
    suspend fun getLeagues(countryCode: String): List<League>
    suspend fun getTeamByName(name: String): List<Team>
    suspend fun getTeamStatisticsBySeason(league: Int, teamCode: Int, season: Int): TeamStatistic
}