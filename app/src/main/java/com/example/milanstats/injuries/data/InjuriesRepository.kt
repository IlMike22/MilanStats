package com.example.milanstats.injuries.data

import com.example.milanstats.home.data.IFootballApi
import com.example.milanstats.injuries.data.mapper.toInjuries
import com.example.milanstats.injuries.domain.IInjuriesRepository
import com.example.milanstats.injuries.domain.model.Injury

class InjuriesRepository(
    private val api: IFootballApi
) : IInjuriesRepository {
    override suspend fun getPlayerInjuries(teamId: String, season:String): List<Injury> {
        return api.getPlayerInjuries(teamId, season).toInjuries()
    }
}