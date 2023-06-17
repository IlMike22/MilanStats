package com.example.milanstats.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.milanstats.detail.domain.GetTeamDetailsBySeasonUseCase
import com.example.milanstats.detail.domain.model.TableInformation
import com.example.milanstats.detail.domain.use_case.GetTableInformationUseCase
import com.example.milanstats.overview.domain.use_case.GetTeamByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getTeamDetailsBySeason: GetTeamDetailsBySeasonUseCase,
    private val getTeamByName: GetTeamByNameUseCase,
    private val getTableInformation: GetTableInformationUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val teamNameArg: String = checkNotNull(savedStateHandle[TEAM_NAME])

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            if (teamNameArg == "") {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = "Cannot get team name. Argument is null."
                    )

                    return@launch
                }
            }
            try {
                val teamDeferred = getTeamAsync("Ac Milan")
                val team = teamDeferred.await().first()
                getTeamDetailsBySeason(135, team.id, 2022).apply {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = null,
                            isSuccess = TeamDetailData(
                                name = team.name,
                                logo = team.logo,
                                foundedYear = team.founded,
                                teamForm = this.teamForm,
                                penaltyData = this.penalty,
                                tableInformation = TableInformation() // TODO refactor this success state
                            )
                        )
                    }
                }
                val tableInformation = getTableInformation(135, 2022)
                _state.update {
                    it.copy(
                        isSuccess = _state.value.isSuccess.copy(
                            tableInformation = tableInformation
                        )
                    )
                }
            } catch (exception: Exception) {
                _state.update { it.copy(isLoading = false, isError = exception.message) }
            }
        }
    }

    fun onEvent(event: DetailUiEvent) {
        when (event) {
            else -> Unit
        }
    }

    private fun getTeamAsync(name: String) =
        viewModelScope.async {
            getTeamByName(name)
        }

    companion object {
        private const val TEAM_NAME = "teamName"
    }
}