package com.example.milanstats.injuries.domain.model

data class Injury(
    val playerId: Int,
    val name: String,
    val photo: String,
    val reason: String,
    val fixtureId: Int,
    val fixtureDate: String,
    val type: String
) {
    companion object {
        val EMPTY = Injury(
            playerId = -1,
            name = "",
            photo = "",
            reason = "",
            fixtureDate = "",
            fixtureId = -1,
            type = "",
        )
    }
}
