package com.example.milanstats.goalscorer.presentation.state

import com.example.milanstats.injury.data.model.Player

data class GoalScorerDomainResponse(
    val players: List<Player> = emptyList(),
    val errorMessage: String? = null
)

data class Player(
    val name: String,
    val position: String,
    val goals: Int,
    val assists: Int,
    val logo: String
)
