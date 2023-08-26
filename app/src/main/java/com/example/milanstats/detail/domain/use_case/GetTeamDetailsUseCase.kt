package com.example.milanstats.detail.domain

import com.example.milanstats.home.domain.repository.IHomeRepository

class GetTeamDetailsBySeasonUseCase(
    private val repository: IHomeRepository
) {
    suspend operator fun invoke(league: Int, teamCode: Int, season: Int) =
        repository.getTeamStatisticsBySeason(league, teamCode, season)
}