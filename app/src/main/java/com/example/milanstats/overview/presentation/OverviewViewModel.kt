package com.example.milanstats.overview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.milanstats.overview.domain.use_case.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val getCountries: GetCountriesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(OverviewState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val result = getCountries()
                if (result.toString().isNotEmpty()) {
                    _state.update {
                        it.copy(
                            countries = result.toString(),
                            isLoading = false
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