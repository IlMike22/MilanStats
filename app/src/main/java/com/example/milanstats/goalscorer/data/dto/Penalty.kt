package com.example.milanstats.goalscorer.data.dto

data class Penalty(
    val commited: Any,
    val missed: Int,
    val saved: Any,
    val scored: Int,
    val won: Int
)