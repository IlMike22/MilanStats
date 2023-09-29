package com.example.milanstats.goalscorer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.milanstats.goalscorer.domain.use_case.GetGoalScorerUseCase
import com.example.milanstats.goalscorer.presentation.state.GoalScorerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalScorerViewModel @Inject constructor(
    private val getGoalScorers: GetGoalScorerUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(GoalScorerState())
    val state: StateFlow<GoalScorerState> = _state

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val response = getGoalScorers()
                _state.update {
                    it.copy(
                        goalScorers = response,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            } catch (exception: Exception) {
                _state.update {
                    it.copy(
                        goalScorers = emptyList(),
                        isLoading = false,
                        errorMessage = "Unknown error appeared in GoalScorer."
                    )
                }
            }
        }
    }
}