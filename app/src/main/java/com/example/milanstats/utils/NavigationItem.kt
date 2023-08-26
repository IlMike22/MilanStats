package com.example.milanstats.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.milanstats.navigation.Route

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val route: String
)

val items = listOf(
    NavigationItem(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        route = Route.HOME
    ),
    NavigationItem(
        title = "Results",
        selectedIcon = Icons.Filled.List,
        unselectedIcon = Icons.Outlined.List,
        route = Route.RESULTS
    ),
    NavigationItem(
        title = "Table",
        selectedIcon = Icons.Filled.Face,
        unselectedIcon = Icons.Outlined.Face,
        route = Route.TABLE
    ),
    NavigationItem(
        title = "Goalscorers",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        route = Route.GOAL_SCORERS
    ),
    NavigationItem(
        title = "Injuries",
        selectedIcon = Icons.Filled.ThumbUp,
        unselectedIcon = Icons.Outlined.ThumbUp,
        route = Route.INJURIES
    ),
    NavigationItem(
        title = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        route = Route.SETTINGS
    )
)
