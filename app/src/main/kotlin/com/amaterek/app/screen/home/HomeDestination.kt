package com.amaterek.app.screen.home

import com.amaterek.ui.navigation.compose.DestinationRouteWithBuilder
import com.amaterek.ui.navigation.compose.NavigationBuilder
import com.amaterek.ui.navigation.compose.addDestination
import com.amaterek.ui.navigation.destination.ScreenDestination

object HomeDestination : ScreenDestination, DestinationRouteWithBuilder<Unit> {
    override val route: String = "home"

    override fun build(builder: NavigationBuilder) {
        builder.addDestination(HomeDestination) { HomeScreen() }
    }
}