package com.example.milanstats.injury.domain

import com.example.milanstats.injury.domain.model.Injury
import com.example.milanstats.injury.domain.model.InjuryDomainResponse

class GetPlayerInjuryUseCase(
    private val repository: IInjuryRepository
) {
    suspend operator fun invoke(teamId: String, season:String): InjuryDomainResponse {
        return repository.getPlayerInjuries(teamId, season)
    }
}