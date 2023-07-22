package com.example.milanstats.overview.presentation

sealed interface OverviewEvent {
    object CallApiAgain : OverviewEvent
    data class OpenTeamDetails(
        val league: Int,
        val teamId: Int,
        val season: Int
    ) : OverviewEvent

    data class OnSearchTextChanged(val newText: String) : OverviewEvent
    object OnSearchClicked : OverviewEvent
}