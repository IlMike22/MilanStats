package com.example.milanstats.injuries.data.mapper

import com.example.milanstats.injuries.data.model.InjuriesDto
import com.example.milanstats.injuries.data.model.InjuriesResponse
import com.example.milanstats.injuries.domain.model.Injury

fun InjuriesDto.toInjuries(): List<Injury> =
    this.response.map { response ->
        response.toInjury()
    }

fun InjuriesResponse.toInjury() =
    Injury(
        playerId = this.player.id,
        name = this.player.name,
        photo = this.player.photo,
        reason = this.player.reason,
        fixtureId = this.fixture.id,
        fixtureDate = this.fixture.date,
        type = this.player.type
    )
