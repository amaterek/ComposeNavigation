package com.amaterek.app.screen.splash

import com.amaterek.ui.compose.navigation.destination.Destination

interface SplashNavigationCallback {

    suspend operator fun invoke(): Destination
}