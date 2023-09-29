package com.example.milanstats.goalscorer.domain.use_case

import com.example.milanstats.goalscorer.domain.model.GoalScorer
import com.example.milanstats.goalscorer.domain.repository.IGoalScorerRepository

class GetGoalScorerUseCase(
    private val repository: IGoalScorerRepository
) {
    suspend operator fun invoke(): List<GoalScorer> {
        val season = "2023"
        val teamId = "489"
        val leagueId = "135"
        return repository.getGoalScorers(season, leagueId)
    }
}