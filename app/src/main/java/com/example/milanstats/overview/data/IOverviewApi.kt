package com.example.milanstats.overview.data

import com.example.milanstats.common.BASE_URI
import retrofit2.http.GET
import retrofit2.http.Headers

interface IOverviewApi {
    @GET("countries")
    suspend fun getCountries(): Any
}