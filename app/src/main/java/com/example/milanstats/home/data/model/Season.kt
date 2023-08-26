package com.example.milanstats.home.data.model

data class Season(
    val coverage: Coverage,
    val current: Boolean,
    val end: String,
    val start: String,
    val year: Int
)