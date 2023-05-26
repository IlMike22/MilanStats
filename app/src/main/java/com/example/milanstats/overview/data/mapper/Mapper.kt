package com.example.milanstats.overview.data.mapper

import com.example.milanstats.overview.data.model.CountriesResponseDto
import com.example.milanstats.overview.domain.model.Country

fun CountriesResponseDto.toCountries(): List<Country> {
    return this.response.map { countryDto ->
        Country(
            id = countryDto.code,
            flag = countryDto.flag,
            name = countryDto.name
        )
    }
}