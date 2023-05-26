package com.example.milanstats.overview.data.mapper

import com.example.milanstats.overview.data.model.CountriesResponseDto
import com.example.milanstats.overview.data.model.LeaguesResponseDto
import com.example.milanstats.overview.domain.model.Country
import com.example.milanstats.overview.domain.model.League

fun CountriesResponseDto.toCountries(): List<Country> {
    return this.response.map { countryDto ->
        Country(
            id = countryDto.code,
            flag = countryDto.flag,
            name = countryDto.name
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