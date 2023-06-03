package com.example.milanstats.overview.data.mapper

import com.example.milanstats.overview.data.model.CountriesResponseDto
import com.example.milanstats.overview.data.model.LeaguesResponseDto
import com.example.milanstats.overview.data.model.teams.Team
import com.example.milanstats.overview.domain.model.Country
import com.example.milanstats.overview.domain.model.League
import com.example.milanstats.overview.data.model.Country as CountryData
import com.example.milanstats.overview.data.model.League as LeagueData
import com.example.milanstats.overview.domain.model.Team as TeamDomain

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
            name = leagueDto.league.name,
            logo = leagueDto.league.logo,
            type = leagueDto.league.type
        )
    }
}

fun List<LeagueData>.toLeagues(): List<League> {
    return this.map { league ->
        League(
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

fun Team.toDomainTeam(): TeamDomain =
    TeamDomain(
        name = this.name,
        logo = this.logo,
        country = this.country,
        founded = this.founded
    )