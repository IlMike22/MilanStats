package com.example.milanstats.injuries.presentation

import com.example.milanstats.injuries.domain.model.Injury

data class InjuriesState(
    val injuries: List<Injury> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
