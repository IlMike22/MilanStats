package com.example.milanstats.detail.data.model

import com.example.milanstats.overview.data.model.teams.Team

data class Standing(
    val all: All,
    val away: Away,
    val description: String?,
    val form: String,
    val goalsDiff: Int,
    val group: String,
    val home: Home,
    val points: Int,
    val rank: Int,
    val status: String,
    val team: Team , // important! you use currently the team data from overview
    val update: String
)
