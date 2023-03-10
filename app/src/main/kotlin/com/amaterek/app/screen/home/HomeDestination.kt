package com.amaterek.app.screen.home

import com.amaterek.ui.compose.navigation.DestinationRoute
import com.amaterek.ui.compose.navigation.NavigationBuilder
import com.amaterek.ui.compose.navigation.addDestination
import com.amaterek.ui.compose.navigation.destination.ScreenDestination

object HomeDestination : ScreenDestination, DestinationRoute<Unit> {
    override val route: String = "home"

    override fun build(builder: NavigationBuilder) {
        builder.addDestination(HomeDestination) { HomeScreen() }
    }
}