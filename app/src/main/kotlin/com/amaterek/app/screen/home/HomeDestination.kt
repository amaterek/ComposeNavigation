package com.amaterek.app.screen.home

import com.amaterek.util.compose.navigation.DestinationRoute
import com.amaterek.util.compose.navigation.NavigationBuilder
import com.amaterek.util.compose.navigation.addDestination
import com.amaterek.util.compose.navigation.destination.ScreenDestination

object HomeDestination : ScreenDestination, DestinationRoute<Unit> {
    override val route: String = "home"

    override fun build(builder: NavigationBuilder) {
        builder.addDestination(HomeDestination) { HomeScreen() }
    }
}