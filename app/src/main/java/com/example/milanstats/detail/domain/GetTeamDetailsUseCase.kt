package com.example.milanstats.detail.domain

import com.example.milanstats.overview.domain.repository.IOverviewRepository

class GetTeamDetailsBySeasonUseCase(
    private val repository: IOverviewRepository
) {
    suspend operator fun invoke(league: Int, teamCode: Int, season: Int) =
        repository.getTeamStatisticsBySeason(league, teamCode, season)
}