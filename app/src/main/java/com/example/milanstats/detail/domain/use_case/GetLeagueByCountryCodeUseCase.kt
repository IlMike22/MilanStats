package com.example.milanstats.detail.domain.use_case

import com.example.milanstats.detail.domain.repository.IDetailRepository

class GetLeagueByCountryCodeUseCase(
    private val repository: IDetailRepository
) {
    suspend operator fun invoke(code: String): Int {
        return repository.getLeagueByCountryCode(code)
    }
}