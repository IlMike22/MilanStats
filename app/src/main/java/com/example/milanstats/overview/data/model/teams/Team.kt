package com.example.milanstats.overview.data.model.teams

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class Team(
    @PrimaryKey val id: Int,
    val code: String,
    val country: String,
    val founded: Int,
    val logo: String,
    val name: String,
    val national: Boolean
)