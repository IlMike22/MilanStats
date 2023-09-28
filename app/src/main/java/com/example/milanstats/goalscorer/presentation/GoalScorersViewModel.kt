package com.example.milanstats.goalscorer.presentation

import androidx.lifecycle.ViewModel
import com.example.milanstats.goalscorer.presentation.state.GoalScorerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GoalScorersViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(GoalScorerState())
    val state: StateFlow<GoalScorerState> = _state

    init {

    }
}