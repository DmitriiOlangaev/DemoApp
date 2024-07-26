package com.demo.demoapp.presentation.mock.compose.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.demo.demoapp.presentation.mock.compose.MockScreen

const val MOCK_ROUTE = "mock"

fun NavController.navigateToMock(navOptions: NavOptions) = navigate(route = MOCK_ROUTE, navOptions = navOptions)

fun NavGraphBuilder.mockScreen() {
    composable(route = MOCK_ROUTE) {
        MockScreen()
    }
}