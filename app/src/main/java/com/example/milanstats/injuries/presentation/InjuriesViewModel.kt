package com.example.milanstats.injuries.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class InjuriesViewModel(): ViewModel() {
    private var _injuriesState = MutableStateFlow(InjuriesState())
    val injuriesState = _injuriesState.asStateFlow()

}