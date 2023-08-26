package com.example.milanstats.table.domain.repository

import com.example.milanstats.table.domain.model.TableInformation

interface ITableRepository {
    suspend fun getTableInformation(league: Int, season: Int): TableInformation
}