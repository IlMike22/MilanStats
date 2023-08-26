package com.example.milanstats.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.milanstats.detail.domain.GetTeamDetailsBySeasonUseCase
import com.example.milanstats.table.domain.model.TableInformation
import com.example.milanstats.detail.domain.use_case.GetLeagueByCountryCodeUseCase
import com.example.milanstats.table.domain.usecase.GetTableInformationUseCase
import com.example.milanstats.home.domain.use_case.GetTeamByNameUseCase
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
    private val getLeagueByCountryCode: GetLeagueByCountryCodeUseCase,
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
                        errorMessage = "Cannot get team name. Argument is null."
                    )
                    return@launch
                }
            }
            try {
                val teamDeferred = getTeamAsync(teamNameArg)
                val team = teamDeferred.await().first()
                val leagueDeferred = getLeagueAsync(team.country)
                val league = leagueDeferred.await()
                getTeamDetailsBySeason(league, team.id, 2022).apply {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            teamDetails = TeamDetailData(
                                name = team.name,
                                logo = team.logo,
                                foundedYear = team.founded,
                                teamForms = this.teamForms,
                                penaltyData = this.penalty,
                                tableInformation = TableInformation()
                            )
                        )
                    }
                }
                val tableInformation = getTableInformation(league, 2022)
                _state.update {
                    it.copy(
                        teamDetails = _state.value.teamDetails.copy(
                            tableInformation = tableInformation
                        )
                    )
                }
            } catch (exception: Exception) {
                _state.update { it.copy(isLoading = false, errorMessage = exception.message) }
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

    private fun getLeagueAsync(countryCode: String) =
        viewModelScope.async {
            getLeagueByCountryCode(countryCode)
        }

    companion object {
        private const val TEAM_NAME = "teamName"
    }
}