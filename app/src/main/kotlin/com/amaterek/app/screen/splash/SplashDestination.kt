package com.amaterek.app.screen.splash

import com.amaterek.ui.compose.navigation.DestinationRoute
import com.amaterek.ui.compose.navigation.NavigationBuilder
import com.amaterek.ui.compose.navigation.addDestination
import com.amaterek.ui.compose.navigation.destination.ScreenDestination

object SplashDestination : ScreenDestination, DestinationRoute<Unit> {
    override val route: String = "splash"

    override fun build(builder: NavigationBuilder) {
        builder.addDestination(SplashDestination) { SplashScreen() }
    }
}