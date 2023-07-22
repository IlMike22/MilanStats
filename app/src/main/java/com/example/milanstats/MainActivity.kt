package com.example.milanstats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.milanstats.detail.presentation.DetailViewModel
import com.example.milanstats.detail.presentation.screen.DetailScreen
import com.example.milanstats.navigation.Route
import com.example.milanstats.overview.presentation.screen.OverviewScreen
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
                    composable(Route.OVERVIEW) {
                        val viewModel = hiltViewModel<OverviewViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        OverviewScreen(
                            state = state,
                            onEvent = viewModel::onEvent,
                            navController = navController
                        )
                    }
                    composable(
                        Route.DETAIL + "?teamName={$NAVIGATION_ARGUMENT_TEAM_NAME}",
                        arguments = listOf(navArgument(
                            name = NAVIGATION_ARGUMENT_TEAM_NAME
                        ) {
                            type = NavType.StringType
                            defaultValue = ""
                        })
                    ) {
                        val viewModel = hiltViewModel<DetailViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        DetailScreen(
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                }
            }
        }
    }

    companion object {
        private const val NAVIGATION_ARGUMENT_TEAM_NAME = "teamName"
    }
}
