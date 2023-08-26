package com.example.milanstats.home.domain.use_case

import com.example.milanstats.home.domain.model.TeamStatistic
import com.example.milanstats.home.domain.repository.IHomeRepository

class GetTeamStatisticsUseCase(
    val repository: IHomeRepository
) {
    suspend operator fun invoke(leagueCode: Int, teamCode: Int, season: Int): TeamStatistic =
        repository.getTeamStatisticsBySeason(leagueCode, teamCode, season)

}

