package com.example.milanstats.detail.data.repository

import com.example.milanstats.detail.data.mapper.toTableInformation
import com.example.milanstats.detail.domain.model.TableInformation
import com.example.milanstats.detail.domain.repository.IDetailRepository
import com.example.milanstats.overview.data.IFootballApi

class DetailRepository(
    private val api: IFootballApi
) : IDetailRepository {
    override suspend fun getTableInformation(league: Int, season: Int): TableInformation {
        return api.getStandings(season.toString(), league.toString()).toTableInformation()
    }

    override suspend fun getLeagueByCountryCode(code: String): Int {
        val response = api.getLeagueByCountryCode(code)
        return response.response.first().league.id
    }

}