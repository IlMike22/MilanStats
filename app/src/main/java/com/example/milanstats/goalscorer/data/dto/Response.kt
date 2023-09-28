package com.example.milanstats.goalscorer.data.dto

data class Response(
    val player: Player,
    val statistics: List<Statistic>
)