package com.example.milanstats.goalscorer.domain.repository

import com.example.milanstats.goalscorer.presentation.state.GoalScorerDomainResponse

interface IGoalScorerRepository {
    suspend fun getGoalScorers():GoalScorerDomainResponse
}