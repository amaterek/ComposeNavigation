package com.amaterek.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.amaterek.ui.navigation.destination.ControlDestinations
import com.amaterek.ui.navigation.destination.Destination
import com.amaterek.ui.navigation.destination.ExternalDestination
import com.amaterek.ui.navigation.destination.PopUpToDestination
import com.amaterek.ui.navigation.destination.PreviousDestination
import com.amaterek.ui.navigation.destination.PreviousDestinationWithResult
import com.amaterek.ui.navigation.destination.ReplaceBackStackDestination
import com.amaterek.ui.navigation.destination.ReplaceCurrentDestination
import com.amaterek.ui.navigation.destination.ScreenDestination

@Stable
class JetpackNavigator internal constructor(
    internal val navController: NavHostController,
    private val externalNavigator: Navigator?,
) : Navigator {

    override fun navigateTo(destination: Destination): Boolean {
        Log.d(Tag, "navigateTo: $destination, canNavigateBack=${canNavigateBack()}")
        return when (destination) {
            is ControlDestinations -> navController.handleControlDestination(destination)
            is ScreenDestination -> {
                navController.navigate(destination)
                true
            }
            is ExternalDestination ->
                externalNavigator?.navigateTo(destination.external) ?: run {
                    Log.d(
                        Tag,
                        "Requested external destination but there is no external navigator"
                    )
                    false
                }
            else -> false
        }
    }

    override fun canNavigateBack(): Boolean =
        navController.backQueue.size > 2

    private fun NavController.handleControlDestination(destination: ControlDestinations): Boolean =
        when (destination) {
            PreviousDestination -> {
                if (canNavigateBack()) {
                    popBackStack()
                } else {
                    externalNavigator?.navigateTo(destination) ?: false
                }
            }
            is PreviousDestinationWithResult<*> -> {
                setNavigationResult(
                    key = destination.key,
                    result = destination.result,
                )
                popBackStack()
            }
            is ReplaceCurrentDestination -> {
                currentNavDestination?.let { navDestination ->
                    navigate(destination = destination.destination) {
                        popUpTo(id = navDestination.id) {
                            inclusive = true
                        }
                    }
                } ?: navigate(destination = destination.destination)
                true
            }
            is ReplaceBackStackDestination -> {
                firstNavDestination?.let { navDestination ->
                    navigate(destination = destination.destination) {
                        popUpTo(id = navDestination.id)
                    }
                } ?: navigate(destination = destination.destination)
                true
            }
            is PopUpToDestination -> {
                navigate(destination = destination.destination) {
                    popUpTo(route = destination.popUpTo.route) {
                        inclusive = destination.inclusive
                    }
                }
                true
            }
        }

    private fun <T> setNavigationResult(key: String, result: T) {
        val previousBackStackEntry = requireNotNull(navController.previousBackStackEntry)
        val savedStateHandle = previousBackStackEntry.savedStateHandle
        savedStateHandle[key] = result
    }

    companion object {
        private const val Tag = "Navigator"
    }
}

private fun NavController.navigate(destination: ScreenDestination) {
    navigate(destination.route)
}

private fun NavController.navigate(
    destination: ScreenDestination,
    builder: NavOptionsBuilder.() -> Unit,
) {
    navigate(destination.route, navOptions(builder))
}

val LocalNavigator = staticCompositionLocalOf<Navigator> {
    throw IllegalStateException("Local navigator hasn't been provided")
}

@Composable
fun rememberNavigator(externalNavigator: Navigator? = null): JetpackNavigator {
    val navController = rememberNavController()
    return remember { createNavigator(navController, externalNavigator) }
}

fun createNavigator(
    navHostController: NavHostController,
    externalNavigator: Navigator? = null,
): JetpackNavigator {
    return JetpackNavigator(
        navController = navHostController,
        externalNavigator = externalNavigator,
    )
}

@Suppress("NOTHING_TO_INLINE")
private inline val NavController.currentNavDestination: NavDestination?
    get() = currentBackStackEntry?.destination

@Suppress("NOTHING_TO_INLINE")
private inline val NavController.firstNavDestination: NavDestination?
    get() = backQueue.firstOrNull()?.destination