package com.example.milanstats.home.domain.use_case

import com.example.milanstats.home.domain.model.Team
import com.example.milanstats.home.domain.repository.IHomeRepository

class GetTeamByNameUseCase(
    private val repository: IHomeRepository
) {
    suspend operator fun invoke(name: String): List<Team> {
        return repository.getTeamByName(name)
    }
}