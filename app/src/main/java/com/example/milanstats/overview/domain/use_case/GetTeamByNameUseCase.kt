package com.example.milanstats.overview.domain.use_case

import com.example.milanstats.overview.domain.model.Team
import com.example.milanstats.overview.domain.repository.IOverviewRepository

class GetTeamByNameUseCase(
    private val repository: IOverviewRepository
) {
    suspend operator fun invoke(name: String): List<Team> {
        return repository.getTeamByName(name)
    }
}