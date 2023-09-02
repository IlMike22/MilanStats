package com.example.milanstats.injuries.domain

import com.example.milanstats.injuries.domain.model.Injury

interface IInjuriesRepository {
    suspend fun getPlayerInjuries(teamId: String, season: String): List<Injury>
}