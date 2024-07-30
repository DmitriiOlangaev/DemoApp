package com.demo.demoapp.presentation.air.tickets.compose.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.demo.demoapp.presentation.air.tickets.compose.ui.AirTicketsRoute

const val AIR_TICKETS_ROUTE = "air_tickets"

fun NavController.navigateToAirTickets(navOptions: NavOptions) =
    navigate(route = AIR_TICKETS_ROUTE, navOptions = navOptions)

fun NavGraphBuilder.airTicketsScreen() {
    composable(route = AIR_TICKETS_ROUTE) {
        AirTicketsRoute()
    }
}
