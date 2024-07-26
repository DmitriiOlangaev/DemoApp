package com.demo.demoapp.compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.invalidateGroupsWithKey
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.demo.demoapp.compose.navigation.AppNavHost
import com.demo.demoapp.compose.navigation.TopLevelDestination
import com.demo.demoapp.core.designsystem.compose.Blue
import com.demo.demoapp.core.designsystem.compose.Grey1
import com.demo.demoapp.core.designsystem.compose.Tab

@Composable
fun App(appState: AppState, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        containerColor = Color.Transparent,
        bottomBar = {
            AppBottomBar(
                destinations = appState.topLevelDestinations,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
                currentDestination = appState.currentDestination
            )
        }
    ) { padding ->
        AppNavHost(
            appState = appState,
            modifier = Modifier
                .padding(padding)
                .consumeWindowInsets(padding)
        )
    }
}

@Composable
private fun AppBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
) {
    NavigationBar(containerColor = Grey1) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestination(destination)
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon =  { Icon(
                    painter = painterResource(id = destination.selectedId),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )},
                label = { Text(text = stringResource(id = destination.iconTextId), style = Tab) },
                colors = NavigationBarItemDefaults.colors(
                   selectedIconColor = Blue, indicatorColor = Grey1
                )
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestination(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false