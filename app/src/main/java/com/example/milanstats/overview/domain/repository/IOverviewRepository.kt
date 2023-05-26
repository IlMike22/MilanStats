package com.example.milanstats.overview.domain.repository

import com.example.milanstats.overview.domain.model.Country

interface IOverviewRepository {
    suspend fun getCountries(): List<Country>
}