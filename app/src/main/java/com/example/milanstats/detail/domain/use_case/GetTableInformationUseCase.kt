package com.example.milanstats.detail.domain.use_case

import com.example.milanstats.detail.domain.model.TableInformation
import com.example.milanstats.detail.domain.repository.IDetailRepository

class GetTableInformationUseCase(
    private val repository: IDetailRepository
) {
    suspend operator fun invoke(league: Int, season: Int): TableInformation {
        return repository.getTableInformation(league, season)
    }

}