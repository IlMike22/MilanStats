package com.example.milanstats.home.domain.model

data class Country(
    val id: String,
    val flag: String,
    val name: String
) {
    companion object {
        val EMPTY = Country(
            id = "",
            flag = "",
            name = ""
        )
    }
}

