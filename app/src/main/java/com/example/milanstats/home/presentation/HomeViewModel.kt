package com.example.milanstats.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.milanstats.table.domain.usecase.GetTableInformationUseCase
import com.example.milanstats.home.domain.model.League
import com.example.milanstats.home.domain.use_case.GetCountriesUseCase
import com.example.milanstats.home.domain.use_case.GetLeaguesUseCase
import com.example.milanstats.home.domain.use_case.GetTeamByNameUseCase
import com.example.milanstats.home.domain.use_case.GetTeamStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCountries: GetCountriesUseCase, // TODO remove this. generate relevant domain data in uc or repo
    private val getLeague: GetLeaguesUseCase,
    private val getTeamByName: GetTeamByNameUseCase,
    private val getTeamStatistics: GetTeamStatisticsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        setupGreetingText()

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val countries = getCountries()
                val leagues = getLeague(countries.first().id)
                val leagueCode = leagues.find {
                    it.name == "Serie A"
                } ?: League(0, "", "", "")
                val teamResult = getTeamByName("ac milan").first()

                // eg team code 489, season = current like 2023
                val statistics = getTeamStatistics(leagueCode.id, teamResult.id, 2023)
                if (countries.toString().isNotEmpty()) {
                    _state.update {
                        it.copy(
                            teamCountry = countries.first(),
                            isLoading = false,
                            teamName = teamResult.name,
                            teamLogo = teamResult.logo,
                            teamStatistic = statistics,
                        )
                    }
                }
            } catch (exception: Exception) {
                _state.update {
                    it.copy(
                        errorMessage = "Error. Details: ${exception.message}",
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun setupGreetingText() {
        val currentDateTime = LocalDateTime.now()
        var greetingText = ""
        greetingText = if (currentDateTime.hour >= 12) {
            "Good afternoon"
        } else if (currentDateTime.hour in 17..20) {
            "Good evening"
        } else if (currentDateTime.hour in 21..23) {
            "Good Night"
        } else {
            "Good morning!"
        }

        _state.update {
            it.copy(
                greetingsText = greetingText
            )
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OpenTeamDetails -> {
                // TODO navigate to DetailScreen with parameters
            }

            is HomeEvent.OnSearchTextChanged -> {
                _state.update { it.copy(searchText = event.newText) }
            }

            HomeEvent.OnSearchClicked -> {
                viewModelScope.launch {

                }
            }
        }
    }
}