package com.example.milanstats.detail.presentation

import com.example.milanstats.detail.domain.model.TableInformation
import com.example.milanstats.overview.domain.model.Penalty
import com.example.milanstats.overview.domain.model.TeamForm

data class DetailState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val teamDetails: TeamDetailData = TeamDetailData.EMPTY
)

data class TeamDetailData(
    val name: String,
    val logo: String,
    val foundedYear: Int,
    val teamForms: List<TeamForm>,
    val penaltyData: Penalty,
    val tableInformation: TableInformation
) {
    companion object {
        val EMPTY = TeamDetailData(
            name = "",
            logo = "",
            foundedYear = 0,
            teamForms = emptyList(),
            penaltyData = Penalty.EMPTY,
            tableInformation = TableInformation()
        )
    }
}

