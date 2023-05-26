package com.example.milanstats.overview.data.repository

import com.example.milanstats.overview.data.IOverviewApi
import com.example.milanstats.overview.data.mapper.toCountries
import com.example.milanstats.overview.data.mapper.toLeagues
import com.example.milanstats.overview.domain.model.Country
import com.example.milanstats.overview.domain.model.League
import com.example.milanstats.overview.domain.repository.IOverviewRepository

class OverviewRepository(
    private val api: IOverviewApi
) : IOverviewRepository {
    override suspend fun getCountries(): List<Country> {
        return api.getCountries(name = "italy").toCountries()
    }

    override suspend fun getLeagues(counryCode: String): List<League> {
        return api.getLeagues(countryCode = counryCode).toLeagues()
    }
}