package com.example.milanstats.home.presentation

sealed interface HomeEvent {
    data class OpenTeamDetails(
        val league: Int,
        val teamId: Int,
        val season: Int
    ) : HomeEvent

    data class OnSearchTextChanged(val newText: String) : HomeEvent
    object OnSearchClicked : HomeEvent
}