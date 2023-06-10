package com.example.milanstats.overview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.milanstats.overview.domain.model.League
import com.example.milanstats.overview.domain.use_case.GetCountriesUseCase
import com.example.milanstats.overview.domain.use_case.GetLeaguesUseCase
import com.example.milanstats.overview.domain.use_case.GetTeamByNameUseCase
import com.example.milanstats.overview.domain.use_case.GetTeamStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val getCountries: GetCountriesUseCase, // TODO remove this. generate relevant domain data in uc or repo
    private val getLeague: GetLeaguesUseCase,
    private val getTeamByName: GetTeamByNameUseCase,
    private val getTeamStatistics: GetTeamStatisticsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(OverviewState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val countries = getCountries()
                val leagues = getLeague(countries.first().id)
                val leagueCode = leagues.find {
                    it.name == "Serie A"
                } ?: League(0, "", "", "")
                val teams = getTeamByName("ac milan")

//                val statistics = getTeamStatistics(leagueCode.id, 489, 2019) // TODO MIC get team id correctly with previous api call
                if (countries.toString().isNotEmpty()) {
                    _state.update {
                        it.copy(
                            countries = countries,
                            teams = teams,
                            isLoading = false,
                            leagues = leagues,
//                            teamStatistic = statistics
                        )
                    }
                }
            } catch (exception: Exception) {
                _state.update {
                    it.copy(
                        error = "Error. Details: ${exception.message}",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onEvent(event: OverviewEvent) {
        when (event) {
            OverviewEvent.CallApiAgain -> { // TODO MIC just for testing. remove later!
                viewModelScope.launch {
                    val countries = getCountries()
                    val leagues = getLeague(countries.first().id)
                    val leagueCode = leagues.find {
                        it.name == "Serie A"
                    } ?: League(0, "", "", "")
                    val teams = getTeamByName("ac milan")

                    val statistics = getTeamStatistics(135, 489, 2021)
                }
            }

            is OverviewEvent.OpenTeamDetails -> {
                // TODO navigate to DetailScreen with parameters
            }
        }
    }
}