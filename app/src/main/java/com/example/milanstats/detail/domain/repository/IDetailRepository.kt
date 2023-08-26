package com.example.milanstats.detail.domain.repository

import com.example.milanstats.table.domain.model.TableInformation

interface IDetailRepository {
    suspend fun getLeagueByCountryCode(code: String): Int
}