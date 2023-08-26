package com.example.milanstats.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.milanstats.home.data.model.Country

@Dao
interface ICountryDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertCountry(country: Country)

    @Query("SELECT * FROM countries")
    suspend fun getCountries(): List<Country>

    @Query("SELECT * FROM countries WHERE name = :name")
    suspend fun getCountryByName(name: String): Country?
}