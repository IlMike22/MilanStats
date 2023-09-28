package com.example.milanstats.table.data.repository

import com.example.milanstats.home.data.IFootballApi
import com.example.milanstats.table.data.mapper.toTableInformation
import com.example.milanstats.table.domain.model.TableInformation
import com.example.milanstats.table.domain.repository.ITableRepository

class TableRepository(
    private val api: IFootballApi
) : ITableRepository {
    override suspend fun getTableInformation(league: Int, season: Int): TableInformation {
        return api.getStandings(season.toString(), league.toString()).toTableInformation()
    }
}