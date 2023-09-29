package com.example.milanstats.goalscorer.data.repository

import com.example.milanstats.goalscorer.data.mapper.toDomainGoalScorers
import com.example.milanstats.goalscorer.domain.model.GoalScorer
import com.example.milanstats.goalscorer.domain.repository.IGoalScorerRepository
import com.example.milanstats.home.data.IFootballApi

class GoalScorerRepository(
    private val api: IFootballApi
) : IGoalScorerRepository {
    override suspend fun getGoalScorers(season: String, leagueId: String): List<GoalScorer> {
        return api.getGoalScorers(season, leagueId).toDomainGoalScorers()
    }
}