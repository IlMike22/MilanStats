package com.example.milanstats.overview.data.repository

import com.example.milanstats.overview.data.IOverviewApi
import com.example.milanstats.overview.domain.repository.IOverviewRepository

class OverviewRepository(
    private val api: IOverviewApi
) : IOverviewRepository {
    override suspend fun getCountries(): Any {
        val result =  api.getCountries()
        return result
    }
}