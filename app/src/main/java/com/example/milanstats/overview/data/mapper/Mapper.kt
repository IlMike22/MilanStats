package com.example.milanstats.overview.data.mapper

import com.example.milanstats.overview.data.model.CountriesResponseDto
import com.example.milanstats.overview.data.model.LeaguesResponseDto
import com.example.milanstats.overview.data.model.teamstatistics.Penalty
import com.example.milanstats.overview.data.model.teamstatistics.TeamsStatisticsResponse
import com.example.milanstats.overview.domain.model.Country
import com.example.milanstats.overview.domain.model.League
import com.example.milanstats.overview.domain.model.TeamForm
import com.example.milanstats.overview.domain.model.TeamStatistic
import com.example.milanstats.overview.data.model.Country as CountryData
import com.example.milanstats.overview.data.model.League as LeagueData
import com.example.milanstats.overview.domain.model.Penalty as PenaltyDomain

fun CountriesResponseDto.toCountries(): List<Country> {
    return this.response.map { countryDto ->
        Country(
            id = countryDto.code,
            flag = countryDto.flag,
            name = countryDto.name
        )
    }
}

fun CountriesResponseDto.toCountriesData(): List<CountryData> {
    return this.response.map { countryDto ->
        CountryData(
            code = countryDto.code,
            flag = countryDto.flag,
            name = countryDto.name
        )
    }
}

fun List<CountryData>.toCountries(): List<Country> {
    return this.map {
        Country(
            id = it.code,
            flag = it.flag,
            name = it.name
        )
    }
}

fun LeaguesResponseDto.toLeagues(): List<League> {
    return this.response.map { leagueDto ->
        League(
            id = leagueDto.league.id,
            name = leagueDto.league.name,
            logo = leagueDto.league.logo,
            type = leagueDto.league.type
        )
    }
}

fun List<LeagueData>.toLeagues(): List<League> {
    return this.map { league ->
        League(
            id = league.id,
            name = league.name,
            logo = league.logo,
            type = league.type
        )
    }
}

fun League.toLeagueData(): LeagueData {
    return LeagueData(
        logo = this.logo,
        name = this.name,
        type = this.type
    )
}

fun TeamsStatisticsResponse.toDomainStatisticsResponse(): TeamStatistic =
    TeamStatistic(
        teamForms = this.form?.toTeamForms() ?: emptyList(),
        penalty = this.penalty?.toDomainPenalty() ?: PenaltyDomain(0, 0, 0)
    )

fun Penalty.toDomainPenalty(): PenaltyDomain =
    PenaltyDomain(
        totalScored = this.scored.total,
        totalMissed = this.missed.total,
        total = this.total
    )

fun String.toTeamForms(): List<TeamForm> {
    var x = 0
    val forms = mutableListOf<Char>()
    while (x < this.length) {
        forms.add(this[x])
        x++
    }
    return forms.map {
        it.toTeamForms()
    }
}

fun Char.toTeamForms(): TeamForm {
    return when (this) {
        'W' -> TeamForm.WIN
        'D' -> TeamForm.DRAW
        'L' -> TeamForm.LOSE
        else -> TeamForm.UNDEFINED
    }
}


