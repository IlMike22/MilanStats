package com.example.milanstats.injury.domain.model

data class InjuryDomainResponse(
    val injuries: List<Injury> = emptyList(),
    val errorMessage:String? = null
)