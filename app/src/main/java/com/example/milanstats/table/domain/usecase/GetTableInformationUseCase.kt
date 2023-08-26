package com.example.milanstats.table.domain.usecase

import com.example.milanstats.table.domain.model.TableInformation
import com.example.milanstats.detail.domain.repository.IDetailRepository
import com.example.milanstats.table.domain.repository.ITableRepository

class GetTableInformationUseCase(
    private val repository: ITableRepository
) {
    suspend operator fun invoke(league: Int, season: Int): TableInformation {
        return repository.getTableInformation(league, season)
    }
}