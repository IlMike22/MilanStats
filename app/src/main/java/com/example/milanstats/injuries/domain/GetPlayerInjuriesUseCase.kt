package com.example.milanstats.injuries.domain

import com.example.milanstats.injuries.domain.model.Injury

class GetPlayerInjuriesUseCase(
    private val repository: IInjuriesRepository
) {
    suspend operator fun invoke(teamId:String): List<Injury> {
        return repository.getPlayerInjuries(teamId)
    }
}