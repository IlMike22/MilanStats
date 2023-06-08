package com.example.milanstats.overview.domain.use_case

import com.example.milanstats.overview.domain.model.TeamStatistic
import com.example.milanstats.overview.domain.repository.IOverviewRepository

class GetTeamStatisticsUseCase(
    val repository: IOverviewRepository
) {
    suspend operator fun invoke(leagueCode: Int, teamCode: Int, season: Int): TeamStatistic =
        repository.getTeamStatisticsBySeason(leagueCode, teamCode, season)

}

