package com.example.milanstats.overview.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey val code: String,
    val flag: String,
    val name: String
)