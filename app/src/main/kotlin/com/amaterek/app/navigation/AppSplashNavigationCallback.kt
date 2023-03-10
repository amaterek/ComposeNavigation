package com.amaterek.app.navigation

import com.amaterek.app.screen.home.HomeDestination
import com.amaterek.app.screen.splash.SplashNavigationCallback
import com.amaterek.ui.navigation.destination.Destination
import com.amaterek.ui.navigation.destination.replaceCurrent
import kotlinx.coroutines.delay

internal class AppSplashNavigationCallback : SplashNavigationCallback {
    override suspend fun invoke(): Destination {
        delay(1000)
        return HomeDestination.replaceCurrent()
    }
}