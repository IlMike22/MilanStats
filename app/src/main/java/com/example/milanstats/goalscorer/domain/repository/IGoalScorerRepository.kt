package com.example.milanstats.goalscorer.domain.repository

import com.example.milanstats.goalscorer.domain.model.GoalScorer

interface IGoalScorerRepository {
    suspend fun getGoalScorers(season: String, leagueId: String): List<GoalScorer>
}