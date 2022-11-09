package com.amaterek.app.screen.splash

import com.amaterek.util.compose.navigation.DestinationRoute
import com.amaterek.util.compose.navigation.NavigationBuilder
import com.amaterek.util.compose.navigation.addDestination
import com.amaterek.util.compose.navigation.destination.ScreenDestination

object SplashDestination : ScreenDestination, DestinationRoute<Unit> {
    override val route: String = "splash"

    override fun build(builder: NavigationBuilder) {
        builder.addDestination(SplashDestination) { SplashScreen() }
    }
}