package com.example.milanstats.overview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.milanstats.overview.domain.use_case.GetCountriesUseCase
import com.example.milanstats.overview.domain.use_case.GetLeaguesUseCase
import com.example.milanstats.overview.domain.use_case.GetTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val getCountries: GetCountriesUseCase,
    private val getLeague: GetLeaguesUseCase,
    private val getTeams: GetTeamsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(OverviewState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val countries = getCountries()
                val leagues = getLeague(countries.first().id)
                val teams = getTeams(countries.first().id)
                if (countries.toString().isNotEmpty()) {
                    _state.update {
                        it.copy(
                            countries = countries,
                            teams = teams,
                            isLoading = false,
                            leagues = leagues
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
}