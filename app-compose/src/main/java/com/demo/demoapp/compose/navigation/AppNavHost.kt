package com.demo.demoapp.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.demo.demoapp.compose.ui.AppState
import com.demo.demoapp.presentation.air.tickets.compose.navigation.AIR_TICKETS_ROUTE
import com.demo.demoapp.presentation.air.tickets.compose.navigation.airTicketsScreen
import com.demo.demoapp.presentation.mock.compose.navigation.mockScreen

@Composable
fun AppNavHost(
    appState: AppState,
    modifier: Modifier = Modifier,
    startDestination: String = AIR_TICKETS_ROUTE
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        airTicketsScreen()
        mockScreen()
    }
}