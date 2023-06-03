package com.example.milanstats.overview.domain.repository

import com.example.milanstats.overview.domain.model.Country
import com.example.milanstats.overview.domain.model.League
import com.example.milanstats.overview.domain.model.Team

interface IOverviewRepository {
    suspend fun getCountries(countryName: String): List<Country>
    suspend fun getLeagues(countryCode: String): List<League>
    suspend fun getTeamsByCountryName(countryCode: String): List<Team>
}