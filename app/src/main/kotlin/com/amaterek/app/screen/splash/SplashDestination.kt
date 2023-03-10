package com.amaterek.app.screen.splash

import com.amaterek.ui.navigation.compose.DestinationRouteWithBuilder
import com.amaterek.ui.navigation.compose.NavigationBuilder
import com.amaterek.ui.navigation.compose.addDestination
import com.amaterek.ui.navigation.destination.ScreenDestination

object SplashDestination : ScreenDestination, DestinationRouteWithBuilder<Unit> {
    override val route: String = "splash"

    override fun build(builder: NavigationBuilder) {
        builder.addDestination(SplashDestination) { SplashScreen() }
    }
}