package com.example.milanstats.overview.domain.repository

interface IOverviewRepository {
    suspend fun getCountries():Any
}