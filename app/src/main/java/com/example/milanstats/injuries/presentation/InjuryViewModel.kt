package com.example.milanstats.injuries.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.milanstats.injuries.domain.GetPlayerInjuryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InjuryViewModel @Inject constructor(
    private val getPlayerInjuries: GetPlayerInjuryUseCase
) : ViewModel() {
    private var _state = MutableStateFlow(InjuriesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val injuries = getPlayerInjuries(TEAM_ID_AC_MILAN, SEASON)
                _state.update {
                    it.copy(
                        injuries = injuries,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            } catch (exception: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Error occoured while trying to fetch the injuries. Details: ${exception.message}"
                    )
                }
            }

        }
    }

    companion object {
        private const val TEAM_ID_AC_MILAN = "489"
        private const val SEASON = "2023"
    }
}