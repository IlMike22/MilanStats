package com.example.milanstats.overview.presentation

sealed interface OverviewEvent {
    object CallApiAgain : OverviewEvent
}