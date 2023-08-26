package com.example.milanstats.home.data.repository

import com.example.milanstats.common.mapper.toDomainTeam
import com.example.milanstats.db.ICountryDao
import com.example.milanstats.db.ILeagueDao
import com.example.milanstats.home.data.IFootballApi
import com.example.milanstats.home.data.mapper.toCountries
import com.example.milanstats.home.data.mapper.toCountriesData
import com.example.milanstats.home.data.mapper.toDomainStatisticsResponse
import com.example.milanstats.home.data.mapper.toLeagues
import com.example.milanstats.home.domain.model.Country
import com.example.milanstats.home.domain.model.League
import com.example.milanstats.home.domain.model.Team
import com.example.milanstats.home.domain.model.TeamStatistic
import com.example.milanstats.home.domain.repository.IHomeRepository
import com.example.milanstats.home.data.model.League as LeagueDto

class HomeRepository(
    private val api: IFootballApi,
    private val countryDao: ICountryDao,
    private val leagueDao: ILeagueDao
) : IHomeRepository {
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

        val leagueResponse = api.getLeagues(countryCode = countryCode)
        leagueResponse.response.map {
            leagueDao.insertLeague(
                LeagueDto(
                    id = it.league.id,
                    logo = it.league.logo,
                    name = it.league.name,
                    type = it.league.type
                )
            )
        }

        return leagueResponse.toLeagues()
    }

    override suspend fun getTeamByName(name: String): List<Team> {
        return api.getTeamByName(name).response.map { it.team.toDomainTeam() }.toList()
    }

    override suspend fun getTeamStatisticsBySeason(
        league: Int,
        teamCode: Int,
        season: Int
    ): TeamStatistic {
        return api.getTeamStatisticsBySeason(
            league,
            teamCode,
            season
        ).response.toDomainStatisticsResponse()
    }
}