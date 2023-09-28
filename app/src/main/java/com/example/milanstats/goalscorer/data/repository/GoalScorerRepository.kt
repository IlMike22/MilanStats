package com.example.milanstats.goalscorer.data.repository

import com.example.milanstats.goalscorer.domain.repository.IGoalScorerRepository
import com.example.milanstats.goalscorer.presentation.state.GoalScorerDomainResponse
import com.example.milanstats.home.data.IFootballApi

class GoalScorerRepository(
    private val api: IFootballApi
):IGoalScorerRepository {
    override suspend fun getGoalScorers(): GoalScorerDomainResponse {
        return api.getGoalScorers().toDomainGoalScorers()
    }
}