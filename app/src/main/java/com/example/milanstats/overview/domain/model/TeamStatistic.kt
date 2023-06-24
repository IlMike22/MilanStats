package com.example.milanstats.overview.domain.model

data class TeamStatistic(
    val teamForms: List<TeamForm>,
    val penalty: Penalty
) {
    companion object {
        val EMPTY = TeamStatistic(
                teamForms = emptyList(),
            penalty = Penalty(0,0,0)
        )
    }
}

enum class TeamForm {
    WIN,
    DRAW,
    LOSE,
    UNDEFINED
}