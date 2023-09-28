package com.example.milanstats.injury.data

import com.example.milanstats.home.data.IFootballApi
import com.example.milanstats.injury.data.mapper.toInjuries
import com.example.milanstats.injury.domain.IInjuryRepository
import com.example.milanstats.injury.domain.model.Injury
import com.example.milanstats.injury.domain.model.InjuryDomainResponse

class InjuryRepository(
    private val api: IFootballApi
) : IInjuryRepository {
    override suspend fun getPlayerInjuries(teamId: String, season:String): InjuryDomainResponse {
        return api.getPlayerInjuries(teamId, season).toInjuries()
    }
}