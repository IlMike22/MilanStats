package com.example.milanstats.home.domain.use_case

import com.example.milanstats.home.domain.model.League
import com.example.milanstats.home.domain.repository.IHomeRepository

class GetLeaguesUseCase(
    private val repository: IHomeRepository
) {
    suspend operator fun invoke(countryCode: String): List<League> {
        return repository.getLeagues(countryCode)
    }
}