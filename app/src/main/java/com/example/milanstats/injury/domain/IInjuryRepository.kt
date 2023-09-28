package com.example.milanstats.injury.domain

import com.example.milanstats.injury.domain.model.Injury
import com.example.milanstats.injury.domain.model.InjuryDomainResponse

interface IInjuryRepository {
    suspend fun getPlayerInjuries(teamId: String, season: String): InjuryDomainResponse
}