package com.example.milanstats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.milanstats.overview.presentation.OverviewScreen
import com.example.milanstats.overview.presentation.OverviewViewModel
import com.example.milanstats.ui.theme.MilanStatsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MilanStatsTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "overview") {
                    composable("overview") {
                        val viewModel = hiltViewModel<OverviewViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        OverviewScreen(state = state)
                    }
                }
            }
        }
    }
}
