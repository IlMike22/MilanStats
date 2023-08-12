package com.example.milanstats.overview.domain.model

data class TeamStatistic(
    val teamForms: List<TeamForm>,
    val penalty: Penalty,
    val biggest: BiggestWinsAndLoses
) {
    companion object {
        val EMPTY = TeamStatistic(
            teamForms = emptyList(),
            penalty = Penalty(0, 0, 0),
            biggest = BiggestWinsAndLoses.EMPTY
        )
    }
}

enum class TeamForm {
    WIN,
    DRAW,
    LOSE,
    UNDEFINED
}

data class BiggestWinsAndLoses(
    val homeWin: String,
    val awayWin: String,
    val homeDefeat: String,
    val awayDefeat: String
) {
    companion object {
        val EMPTY = BiggestWinsAndLoses(
            homeWin = "",
            awayWin = "",
            homeDefeat = "",
            awayDefeat = "",
        )
    }
}