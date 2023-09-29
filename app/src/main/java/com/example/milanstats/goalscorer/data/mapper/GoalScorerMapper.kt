package com.example.milanstats.goalscorer.data.mapper

import com.example.milanstats.goalscorer.data.dto.GoalScorerDto
import com.example.milanstats.goalscorer.data.dto.Response
import com.example.milanstats.goalscorer.data.dto.Statistic
import com.example.milanstats.goalscorer.domain.model.GoalScorer
import com.example.milanstats.goalscorer.domain.model.Statistics

fun GoalScorerDto.toDomainGoalScorers(): List<GoalScorer> {
    return this.response.map { response ->
        GoalScorer(
            id = response.player.id,
            name = response.player.name,
            firstName = response.player.firstname,
            lastName = response.player.lastname,
            age = response.player.age,
            photo = response.player.photo,
            nationality = response.player.nationality,
            statistics = response.statistics.toDomainStatistics().first()
        )
    }
}

fun List<Statistic>.toDomainStatistics(): List<Statistics> =
    this.map { statistic ->
        Statistics(
            teamId = statistic.team.id,
            teamName = statistic.team.name,
            leagueId = statistic.league.id,
            leagueName = statistic.league.name,
            leagueLogo = statistic.league.logo,
            gameAppearances = statistic.games.appearences,
            lineups = statistic.games.lineups,
            minutesPlayed = statistic.games.minutes,
            position = statistic.games.position,
            totalShots = statistic.shots.total,
            shotsOnTarget = statistic.shots.on,
            goals = statistic.goals.total,
            assists = statistic.goals.assists,
            passes = statistic.passes.total,
            tackles = statistic.tackles.total,
            dribbles = statistic.dribbles.success,
            fouls = statistic.fouls.committed,
            yellowCards = statistic.cards.yellow,
            redCards = statistic.cards.red,
            penaltyScored = statistic.penalty.scored
        )
    }

