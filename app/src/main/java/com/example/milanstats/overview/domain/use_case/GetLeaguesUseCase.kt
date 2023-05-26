package com.example.milanstats.overview.domain.use_case

import com.example.milanstats.overview.domain.model.League
import com.example.milanstats.overview.domain.repository.IOverviewRepository

class GetLeaguesUseCase(
    private val repository: IOverviewRepository
) {
    suspend operator fun invoke(countryCode: String): List<League> {
        return repository.getLeagues(countryCode)
    }
}