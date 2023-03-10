package com.amaterek.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.amaterek.ui.compose.navigation.DestinationRoute
import com.amaterek.ui.compose.navigation.LocalNavigator
import com.amaterek.ui.compose.navigation.NavigationBuilder
import com.amaterek.ui.compose.navigation.Navigator
import com.amaterek.ui.compose.navigation.destination.ScreenDestination
import javax.inject.Inject

class AppNavigationHost @Inject constructor(
    private val destinationRouteSet: Set<@JvmSuppressWildcards DestinationRoute<*>>,
) {

    @Composable
    fun buildAppNavigation(navController: NavHostController, startDestination: ScreenDestination) {
        val navigator = remember { Navigator(navController) }
        CompositionLocalProvider(LocalNavigator provides navigator) {
            NavHost(navController = navController, startDestination = startDestination.route) {
                NavigationBuilder(this).also { navigationBuilder ->
                    destinationRouteSet.forEach { destinationRoute ->
                        destinationRoute.build(navigationBuilder)
                    }
                }
            }
        }
    }
}