package com.example.milanstats.goalscorer.domain.model

data class GoalScorer(
    val id: Int,
    val name: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val photo: String,
    val nationality: String,
    val statistics: Statistics
)

data class Statistics(
    val teamId: Int,
    val teamName: String,
    val leagueId: Int,
    val leagueName: String,
    val leagueLogo: String,
    val gameAppearances: Int,
    val lineups: Int,
    val minutesPlayed: Int,
    val position: String,
    val totalShots: Int,
    val shotsOnTarget: Int,
    val goals: Int,
    val assists: Int,
    val passes: Int,
    val tackles: Int,
    val dribbles: Int,
    val fouls: Int,
    val yellowCards: Int,
    val redCards: Int,
    val penaltyScored: Int
)
