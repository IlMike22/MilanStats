package com.example.milanstats.overview.domain.model

data class TeamStatistic(
    val teamForm: String,
    val penalty: Penalty
) {
    companion object {
        val EMPTY = TeamStatistic(
                teamForm = "",
            penalty = Penalty(0,0,0)
        )
    }
}