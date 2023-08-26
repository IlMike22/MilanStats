package com.example.milanstats.table.domain.model

import com.example.milanstats.home.domain.model.Team

/**
Represents an object for showing the table for a league and season
got from standings api endpoint
 */
data class TableInformation(
    val league: String = "",
    val season: String = "",
    val standings: List<Standing> = emptyList()
)

data class Standing(
    val rank: Int = -1,
    val description: String = "",
    val form: String = "",
    val goalsDifference: Int = -1,
    val group: String = "",
    val points: Int = -1,
    val team: Team,
    val update: String = "",
    val standing: StandingInformation
)

data class StandingInformation(
    val informationType: StandingInformationType = StandingInformationType.UNDEFINED,
    val draw: Int = -1,
    val lose: Int = -1,
    val win: Int = -1,
    val played: Int = -1,
    val goalsAgainst: Int = -1,
    val goalsFor: Int = -1
)

enum class StandingInformationType {
    HOME, AWAY, UNDEFINED
}
