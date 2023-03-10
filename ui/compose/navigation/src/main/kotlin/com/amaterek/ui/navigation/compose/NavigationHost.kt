package com.amaterek.ui.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import com.amaterek.ui.navigation.JetpackNavigator
import com.amaterek.ui.navigation.LocalNavigator
import com.amaterek.ui.navigation.destination.ScreenDestination

@Composable
fun NavigationHost(
    startDestination: ScreenDestination,
    navigator: JetpackNavigator,
    build: NavigationBuilder.() -> Unit,
) {
    CompositionLocalProvider(LocalNavigator provides navigator) {
        NavHost(
            navController = navigator.navController,
            startDestination = startDestination.route,
        ) {
            NavigationBuilder(this).build()
        }
    }
}