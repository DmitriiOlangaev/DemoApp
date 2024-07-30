package com.demo.demoapp.compose.navigation

import com.demo.demoapp.compose.R

enum class TopLevelDestination(
    val selectedId: Int,
    val unselectedId: Int,
    val iconTextId: Int,
    val titleTextId: Int
) {
    AIR_TICKETS(
        selectedId = R.drawable.ic_air_tickets,
        unselectedId = R.drawable.ic_air_tickets,
        iconTextId = R.string.air_tickets,
        titleTextId = R.string.air_tickets
    ),
    HOTELS(
        selectedId = R.drawable.ic_hotels,
        unselectedId = R.drawable.ic_hotels,
        iconTextId = R.string.hotels,
        titleTextId = R.string.hotels
    ),
    SHORTLY(
        selectedId = R.drawable.ic_shortly,
        unselectedId = R.drawable.ic_shortly,
        iconTextId = R.string.shortly,
        titleTextId = R.string.shortly
    ),
    SUBSCRIPTIONS(
        selectedId = R.drawable.ic_subscriptions,
        unselectedId = R.drawable.ic_subscriptions,
        iconTextId = R.string.subscriptions,
        titleTextId = R.string.subscriptions
    ),
    PROFILE(
        selectedId = R.drawable.ic_profile,
        unselectedId = R.drawable.ic_profile,
        iconTextId = R.string.profile,
        titleTextId = R.string.profile
    )

}