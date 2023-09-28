package com.example.milanstats.injury.presentation

import com.example.milanstats.injury.domain.model.Injury

data class InjuryState(
    val injuries: List<Injury> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
