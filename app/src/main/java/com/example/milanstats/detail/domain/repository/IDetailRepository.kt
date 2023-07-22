package com.example.milanstats.detail.domain.repository

import com.example.milanstats.detail.domain.model.TableInformation

interface IDetailRepository {
    suspend fun getTableInformation(league: Int, season: Int): TableInformation
    suspend fun getLeagueByCountryCode(code: String): Int
}