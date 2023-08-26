package com.example.milanstats

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.milanstats.detail.presentation.DetailViewModel
import com.example.milanstats.detail.presentation.screen.DetailScreen
import com.example.milanstats.goalscorers.presentation.GoalScorersScreen
import com.example.milanstats.home.presentation.HomeViewModel
import com.example.milanstats.home.presentation.screen.OverviewScreen
import com.example.milanstats.injuries.presentation.InjuriesScreen
import com.example.milanstats.navigation.Route
import com.example.milanstats.results.presentation.ResultsScreen
import com.example.milanstats.settings.presentation.SettingScreen
import com.example.milanstats.table.presentation.TableScreen
import com.example.milanstats.ui.theme.MilanStatsTheme
import com.example.milanstats.utils.items
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MilanStatsTheme {
                val navController = rememberNavController()
                var selectedItemIndex by remember {
                    mutableStateOf(0)
                }
                val scope = rememberCoroutineScope()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ModalNavigationDrawer(
                        drawerContent = {
                            Spacer(modifier = Modifier.height(16.dp))
                            ModalDrawerSheet {
                                items.forEachIndexed { index, item ->
                                    NavigationDrawerItem(
                                        label = { Text(text = item.title) },
                                        selected = index == selectedItemIndex,
                                        onClick = {
                                            selectedItemIndex = index
                                            scope.launch {
                                                drawerState.close()
                                            }
                                            navController.navigate(item.route)
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = if (index == selectedItemIndex) {
                                                    item.selectedIcon
                                                } else item.unselectedIcon,
                                                contentDescription = item.title
                                            )
                                        },
                                        badge = {
                                            item.badgeCount?.let {
                                                Text(text = it.toString())
                                            }
                                        },
                                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                    )
                                }
                            }
                        },
                        drawerState = drawerState
                    ) {
                        Scaffold(
                            topBar = {
                                TopAppBar(
                                    title = {
                                        Text(text = "MilanStats")
                                    },
                                    navigationIcon = {
                                        IconButton(onClick = {
                                            scope.launch {
                                                drawerState.open()
                                            }
                                        }) {
                                            Icon(
                                                imageVector = Icons.Default.Menu,
                                                contentDescription = "Menu"
                                            )
                                        }
                                    }
                                )
                            }
                        ) {
                            NavHost(navController = navController, startDestination = "home") {
                                composable(Route.HOME) {
                                    val viewModel = hiltViewModel<HomeViewModel>()
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
                                composable(Route.RESULTS) {
                                    ResultsScreen()
                                }
                                composable(Route.TABLE) {
                                    TableScreen()
                                }
                                composable(Route.GOAL_SCORERS) {
                                    GoalScorersScreen()
                                }
                                composable(Route.INJURIES) {
                                    InjuriesScreen()
                                }
                                composable(Route.SETTINGS) {
                                    SettingScreen()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val NAVIGATION_ARGUMENT_TEAM_NAME = "teamName"
    }
}
