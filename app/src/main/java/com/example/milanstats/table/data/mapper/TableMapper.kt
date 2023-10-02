package com.example.milanstats.table.data.mapper

import com.example.milanstats.common.mapper.toDomainTeam
import com.example.milanstats.detail.data.model.Standing
import com.example.milanstats.detail.data.model.StandingsDto
import com.example.milanstats.table.domain.model.StandingInformation
import com.example.milanstats.table.domain.model.TableInformation


fun StandingsDto.toTableInformation(): TableInformation {
    if (response.isEmpty()) {
        return TableInformation(errorMessage = errors.first().toString())
    }
    else {
        this.response.first().apply {
            return TableInformation(
                league = league.name,
                season = league.season.toString(),
                standings = this.league.standings.first().toTableInformationStandings(),
            )
        }
    }
}
fun List<Standing>.toTableInformationStandings(): List<com.example.milanstats.table.domain.model.Standing> =
    this.map { standingData ->
        com.example.milanstats.table.domain.model.Standing(
            description = standingData.description ?: "",
            form = standingData.form,
            goalsDifference = standingData.goalsDiff,
            group = standingData.group,
            points = standingData.points,
            team = standingData.team.toDomainTeam(),
            update = standingData.update,
            rank = standingData.rank,
            standing = StandingInformation(
                played = standingData.home.played + standingData.away.played,
                lose = standingData.home.lose + standingData.away.lose,
                win = standingData.home.win + standingData.away.win,
                draw = standingData.home.draw + standingData.away.draw
            )
        )
    }