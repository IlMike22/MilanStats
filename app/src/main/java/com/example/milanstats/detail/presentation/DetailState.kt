package com.example.milanstats.detail.presentation

import com.example.milanstats.detail.domain.model.TableInformation
import com.example.milanstats.overview.domain.model.Penalty

data class DetailState(
    val isLoading: Boolean = false,
    val isError: String? = null,
    val isSuccess: TeamDetailData = TeamDetailData.EMPTY
)

data class TeamDetailData(
    val name: String,
    val logo: String,
    val foundedYear: Int,
    val teamForm: String,
    val penaltyData: Penalty,
    val tableInformation: TableInformation
) {
    companion object {
        val EMPTY = TeamDetailData(
            name = "",
            logo = "",
            foundedYear = 0,
            teamForm = "", // TODO parse team form later by extracting every single char and map it to TeamForm
            penaltyData = Penalty.EMPTY,
            tableInformation = TableInformation()
        )
    }
}

enum class TeamForm {
    WIN,
    DRAW,
    LOSE,
    UNDEFINED
}