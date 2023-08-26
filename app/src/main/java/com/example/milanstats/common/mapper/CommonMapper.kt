package com.example.milanstats.common.mapper

import com.example.milanstats.home.data.model.teams.Team
import com.example.milanstats.home.domain.model.Team as TeamDomain

fun Team.toDomainTeam(): TeamDomain =
    TeamDomain(
        id = this.id,
        code = this.code ?: "",
        name = this.name,
        logo = this.logo,
        country = this.country ?: "",
        founded = this.founded,
        isNationalTeam = this.national
    )