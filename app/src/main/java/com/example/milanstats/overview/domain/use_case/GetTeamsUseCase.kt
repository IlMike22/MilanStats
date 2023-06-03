package com.example.milanstats.overview.domain.use_case

import com.example.milanstats.overview.domain.model.Team
import com.example.milanstats.overview.domain.repository.IOverviewRepository

class GetTeamsUseCase(
    private val repository: IOverviewRepository
) {
    suspend operator fun invoke(countryCode: String): List<Team> {
        return repository.getTeamsByCountryName(countryCode)
    }
}