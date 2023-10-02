package com.example.milanstats.injury.data.model

data class Response(
    val fixture: Fixture,
    val league: League,
    val player: Player,
    val team: Team
)