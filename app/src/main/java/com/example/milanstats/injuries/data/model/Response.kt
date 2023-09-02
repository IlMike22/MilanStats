package com.example.milanstats.injuries.data.model

data class InjuriesResponse(
    val fixture: Fixture,
    val league: League,
    val player: Player,
    val team: Team
)