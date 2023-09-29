package com.example.milanstats.goalscorer.presentation.state

import com.example.milanstats.goalscorer.domain.model.GoalScorer
import com.example.milanstats.injury.data.model.Player

data class GoalScorerState(
    val goalScorers: List<GoalScorer> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage:String? = null
)
