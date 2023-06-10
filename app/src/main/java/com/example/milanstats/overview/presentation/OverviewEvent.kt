package com.example.milanstats.overview.presentation

sealed interface OverviewEvent {
    object CallApiAgain : OverviewEvent
    data class OpenTeamDetails(
        val league: Int,
        val teamId: Int,
        val season: Int
    ) : OverviewEvent
}