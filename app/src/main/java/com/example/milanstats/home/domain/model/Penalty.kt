package com.example.milanstats.home.domain.model

data class Penalty(
    val totalScored: Int,
    val totalMissed: Int,
    val total: Int
) {
    companion object {
        val EMPTY = Penalty(
            totalMissed = 0,
            totalScored = 0,
            total = 0
        )
    }
}
