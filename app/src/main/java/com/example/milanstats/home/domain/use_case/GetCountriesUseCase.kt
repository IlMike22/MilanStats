package com.example.milanstats.home.domain.use_case

import com.example.milanstats.home.domain.model.Country
import com.example.milanstats.home.domain.repository.IHomeRepository

class GetCountriesUseCase(
    private val repository: IHomeRepository
) {
    suspend operator fun invoke(): List<Country> {
        return repository.getCountries("Italy")
    }
}