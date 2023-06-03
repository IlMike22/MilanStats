package com.example.milanstats.di

import android.content.Context
import androidx.room.Room
import com.example.milanstats.db.ICountryDao
import com.example.milanstats.db.ILeagueDao
import com.example.milanstats.db.StatsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {
    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        StatsDatabase::class.java,
        "stats_database"
    ).build()

    @Provides
    @Singleton
    fun provideCountryDao(db: StatsDatabase): ICountryDao {
        return db.getCountryDao()
    }

    @Provides
    @Singleton
    fun provideLeagueDao(db: StatsDatabase): ILeagueDao {
        return db.getLeagueDao()
    }
}