package com.example.milanstats.overview.domain.repository

import com.example.milanstats.overview.domain.model.Country
import com.example.milanstats.overview.domain.model.League

interface IOverviewRepository {
    suspend fun getCountries(): List<Country>
    suspend fun getLeagues(counryCode:String): List<League>
}