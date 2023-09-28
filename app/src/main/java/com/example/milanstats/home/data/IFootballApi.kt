package com.example.milanstats.home.data

import com.example.milanstats.detail.data.model.StandingsDto
import com.example.milanstats.goalscorer.data.dto.GoalScorerDto
import com.example.milanstats.home.data.model.CountriesResponseDto
import com.example.milanstats.home.data.model.LeaguesResponseDto
import com.example.milanstats.home.data.model.teams.TeamsDto
import com.example.milanstats.home.data.model.teamstatistics.TeamsStatisticsDto
import com.example.milanstats.injury.data.model.InjuriesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IFootballApi {
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

    @GET("standings")
    suspend fun getStandings(
        @Query("season") season: String,
        @Query("league") league: String
    ): StandingsDto

    @GET("leagues")
    suspend fun getLeagueByCountryCode(
        @Query("country") countryCode: String
    ): LeaguesResponseDto

    @GET("injuries")
    suspend fun getPlayerInjuries(
        @Query("team") teamId: String,
        @Query("season") season: String
    ): InjuriesDto

    @GET("players/topscorers")
    suspend fun getGoalScorers(
        @Query("team") teamId: String,
        @Query("season") season:String
    ): GoalScorerDto
}