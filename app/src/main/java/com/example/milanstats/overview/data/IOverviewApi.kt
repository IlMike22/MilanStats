package com.example.milanstats.overview.data

import com.example.milanstats.overview.data.model.CountriesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IOverviewApi {
    @GET("countries")
    suspend fun getCountries(
        @Query("name") name:String
    ): CountriesResponseDto
}