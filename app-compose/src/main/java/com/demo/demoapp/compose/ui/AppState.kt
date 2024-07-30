package com.demo.demoapp.compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.demo.demoapp.compose.navigation.TopLevelDestination
import com.demo.demoapp.presentation.air.tickets.compose.navigation.AIR_TICKETS_ROUTE
import com.demo.demoapp.presentation.air.tickets.compose.navigation.navigateToAirTickets
import com.demo.demoapp.presentation.mock.compose.navigation.navigateToMock

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
): AppState = remember(navController) {
    AppState(navController = navController)
}

@Stable
class AppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination
    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            AIR_TICKETS_ROUTE -> TopLevelDestination.AIR_TICKETS
            else -> null
        }
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (topLevelDestination) {
            TopLevelDestination.AIR_TICKETS -> navController.navigateToAirTickets(topLevelNavOptions)
            TopLevelDestination.HOTELS -> navController.navigateToMock(topLevelNavOptions)
            TopLevelDestination.SHORTLY -> navController.navigateToMock(topLevelNavOptions)
            TopLevelDestination.SUBSCRIPTIONS -> navController.navigateToMock(topLevelNavOptions)
            TopLevelDestination.PROFILE -> navController.navigateToMock(topLevelNavOptions)
        }
    }
}