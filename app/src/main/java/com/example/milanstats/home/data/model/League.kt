package com.example.milanstats.home.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leagues")
data class League(
    @PrimaryKey val id: Int = 0,
    val logo: String,
    val name: String,
    val type: String
)