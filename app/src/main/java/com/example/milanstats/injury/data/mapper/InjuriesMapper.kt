package com.example.milanstats.injury.data.mapper

import com.example.milanstats.injury.data.model.InjuryDto
import com.example.milanstats.injury.data.model.Response
import com.example.milanstats.injury.domain.model.Injury
import com.example.milanstats.injury.domain.model.InjuryDomainResponse

fun InjuryDto.toInjuries(): InjuryDomainResponse =
    if (this.errors.isNotEmpty()) {
        InjuryDomainResponse(
            errorMessage = errors.first().toString()
        )
    } else {
        InjuryDomainResponse(
            injuries = this.response.map { response ->
                response.toInjury()
            }
        )
    }
fun Response.toInjury() =
    Injury(
        playerId = this.player.id,
        name = this.player.name,
        photo = this.player.photo,
        reason = this.player.reason,
        fixtureId = this.fixture.id,
        fixtureDate = this.fixture.date,
        type = this.player.type
    )
