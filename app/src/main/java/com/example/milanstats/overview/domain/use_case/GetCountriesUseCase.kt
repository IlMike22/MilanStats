package com.example.milanstats.overview.domain.use_case

import com.example.milanstats.overview.domain.model.Country
import com.example.milanstats.overview.domain.repository.IOverviewRepository

class GetCountriesUseCase(
    private val repository: IOverviewRepository
) {
    suspend operator fun invoke(): List<Country> {
        return repository.getCountries()
    }
}