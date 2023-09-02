package com.example.milanstats.injuries.domain

import com.example.milanstats.injuries.domain.model.Injury

class GetPlayerInjuryUseCase(
    private val repository: IInjuriesRepository
) {
    suspend operator fun invoke(teamId: String, season:String): List<Injury> {
        return repository.getPlayerInjuries(teamId, season)
    }
}