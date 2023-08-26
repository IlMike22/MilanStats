package com.example.milanstats.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.milanstats.home.data.model.Country
import com.example.milanstats.home.data.model.League

@Database(
    entities = [Country::class, League::class],
    version = 1,
    exportSchema = false
)
abstract class StatsDatabase: RoomDatabase() {
    abstract fun getCountryDao(): ICountryDao
    abstract fun getLeagueDao(): ILeagueDao
}