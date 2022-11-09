package com.amaterek.app.screen.splash

import com.amaterek.util.compose.navigation.destination.Destination

interface SplashNavigationCallback {

    suspend operator fun invoke(): Destination
}