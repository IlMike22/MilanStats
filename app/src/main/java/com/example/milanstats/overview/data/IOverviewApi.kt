package com.example.milanstats.overview.data

import com.example.milanstats.overview.data.model.CountriesResponseDto
import com.example.milanstats.overview.data.model.LeaguesResponseDto
import com.example.milanstats.overview.data.model.teams.TeamsDto
import com.example.milanstats.overview.data.model.teamstatistics.TeamsStatisticsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IOverviewApi {
    @GET("countries")
    suspend fun getCountries(
        @Query("name") name: String
    ): CountriesResponseDto

    @GET("leagues")
    suspend fun getLeagues(
        @Query("code") countryCode: String
    ): LeaguesResponseDto

    @GET("teams")
    suspend fun getTeamByName(
        @Query("name") name: String
    ): TeamsDto

    @GET("teams/statistics")
    suspend fun getTeamStatisticsBySeason(
        @Query("league") leagueCode: Int,
        @Query("team") teamCode: Int,
        @Query("season") season: Int,
    ): TeamsStatisticsDto
}