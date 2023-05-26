package com.example.milanstats.overview.domain.use_case

import com.example.milanstats.overview.domain.repository.IOverviewRepository

class GetCountriesUseCase(
    private val repository: IOverviewRepository
) {
    suspend operator fun invoke(): Any {
        return repository.getCountries()
    }
}