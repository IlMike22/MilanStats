package com.example.milanstats.detail.data.mapper

import com.example.milanstats.common.mapper.toDomainTeam
import com.example.milanstats.detail.data.model.Standing
import com.example.milanstats.detail.data.model.StandingsDto
import com.example.milanstats.detail.domain.model.StandingInformation
import com.example.milanstats.detail.domain.model.TableInformation
import com.example.milanstats.detail.domain.model.Standing as DomainStanding

fun StandingsDto.toTableInformation(): TableInformation {
    this.response.first().apply {
        return TableInformation(
            league = league.name,
            season = league.season.toString(),
            standings = this.league.standings.first().toTableInformationStandings()
        )
    }
}

fun List<Standing>.toTableInformationStandings(): List<DomainStanding> =
    this.map { standingData ->
        DomainStanding(
            description = standingData.description?:"",
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
            ) // TODO finish this mapping and extract in into own mapper function for standingInformation
        )
    }


