package com.example.milanstats.table.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.milanstats.table.domain.model.TableInformation
import com.example.milanstats.table.domain.usecase.GetTableInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
    private val getTableInformation: GetTableInformationUseCase
) : ViewModel() {
    private var _state = MutableStateFlow(TableState())
    val state: StateFlow<TableState> = _state

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val tableInformation = getTableInformation(LEAGUE_ID, SEASON)
                _state.update {
                    it.copy(
                        isLoading = false,
                        tableInformation = tableInformation
                    )
                }

            } catch (exception: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        tableInformation = TableInformation(
                            errorMessage = "Unknown error that could not be parsed from api."
                        )
                    )
                }
            }
        }
    }

    companion object {
        private const val LEAGUE_ID = 135
        private const val SEASON = 2023
    }
}