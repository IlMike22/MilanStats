package com.example.milanstats.injury.data.mapper

import com.example.milanstats.injury.data.model.InjuriesDto
import com.example.milanstats.injury.data.model.InjuriesResponse
import com.example.milanstats.injury.domain.model.Injury
import com.example.milanstats.injury.domain.model.InjuryDomainResponse

fun InjuriesDto.toInjuries(): InjuryDomainResponse =
    InjuryDomainResponse(
        injuries = this.response.map { response ->
            response.toInjury()
        },
        errorMessage = this.errors.requests
    )

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
