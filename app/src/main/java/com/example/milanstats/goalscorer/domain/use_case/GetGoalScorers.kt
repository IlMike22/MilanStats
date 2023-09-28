package com.example.milanstats.goalscorer.domain.use_case

import com.example.milanstats.goalscorer.domain.repository.IGoalScorerRepository
import com.example.milanstats.goalscorer.presentation.state.GoalScorerDomainResponse
import com.example.milanstats.table.data.repository.TableRepository

class GetGoalScorersUseCase(
    private val repository: IGoalScorerRepository
) {
    suspend operator fun invoke():GoalScorerDomainResponse {
        repository.getGoalScorers()
    }
}