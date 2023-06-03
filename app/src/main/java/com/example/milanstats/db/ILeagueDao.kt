package com.example.milanstats.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.milanstats.overview.data.model.League

@Dao
interface ILeagueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeague(league: League)

    @Query("SELECT * FROM leagues")
    suspend fun getLeagues(): List<League>

    @Query("SELECT * FROM leagues WHERE name = :name")
    suspend fun getLeagueByName(name: String): League?
}