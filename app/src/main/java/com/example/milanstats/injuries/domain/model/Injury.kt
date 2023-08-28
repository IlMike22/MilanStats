package com.example.milanstats.injuries.domain.model

data class Injury(
    val id: Int,
    val name: String,
    val photo: String,
    val reason: String
) {
    companion object {
        val EMPTY = Injury(
            id = -1,
            name = "",
            photo = "",
            reason = ""
        )
    }
}
