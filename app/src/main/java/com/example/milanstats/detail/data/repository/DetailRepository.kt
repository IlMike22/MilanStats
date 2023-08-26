package com.example.milanstats.detail.data.repository

import com.example.milanstats.detail.domain.repository.IDetailRepository
import com.example.milanstats.home.data.IFootballApi

class DetailRepository(
    private val api: IFootballApi
) : IDetailRepository {
    override suspend fun getLeagueByCountryCode(code: String): Int {
        val response = api.getLeagueByCountryCode(code)
        return response.response.first().league.id
    }

}