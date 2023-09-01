package com.example.milanstats.injuries.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class InjuriesViewModel @Inject constructor(): ViewModel() {
    private var _injuriesState = MutableStateFlow(InjuriesState())
    val injuriesState = _injuriesState.asStateFlow()

}