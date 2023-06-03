package com.example.milanstats.overview.data.repository

import com.example.milanstats.db.ICountryDao
import com.example.milanstats.db.ILeagueDao
import com.example.milanstats.overview.data.IOverviewApi
import com.example.milanstats.overview.data.mapper.toCountries
import com.example.milanstats.overview.data.mapper.toCountriesData
import com.example.milanstats.overview.data.mapper.toLeagueData
import com.example.milanstats.overview.data.mapper.toLeagues
import com.example.milanstats.overview.domain.model.Country
import com.example.milanstats.overview.domain.model.League
import com.example.milanstats.overview.domain.repository.IOverviewRepository

class OverviewRepository(
    private val api: IOverviewApi,
    private val countryDao: ICountryDao,
    private val leagueDao: ILeagueDao
) : IOverviewRepository {
    override suspend fun getCountries(countryName: String): List<Country> {
        val country = countryDao.getCountryByName(countryName)
        if (country != null) {
            return listOf(country).toCountries()
        }
        val countryResponse = api.getCountries(countryName)
        countryResponse.toCountriesData().map {
            countryDao.insertCountry(it)
        }

        return countryResponse.toCountries()
    }

    override suspend fun getLeagues(countryCode: String): List<League> {
        val leagues = leagueDao.getLeagues()
        if (leagues.isNotEmpty()) {
            return leagues.toLeagues()
        }

        val leagueResponse = api.getLeagues(countryCode = countryCode).toLeagues()
        leagueResponse.map {
            leagueDao.insertLeague(it.toLeagueData())
        }

        return leagueResponse
    }
}