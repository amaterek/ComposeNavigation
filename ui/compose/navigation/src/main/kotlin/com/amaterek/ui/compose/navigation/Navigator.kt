package com.amaterek.ui.compose.navigation

import android.util.Log
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.asFlow
import androidx.navigation.NavController
import com.amaterek.ui.compose.navigation.destination.ControlDestinations
import com.amaterek.ui.compose.navigation.destination.Destination
import com.amaterek.ui.compose.navigation.destination.DialogDestination
import com.amaterek.ui.compose.navigation.destination.PreviousDestination
import com.amaterek.ui.compose.navigation.destination.PreviousDestinationWithResult
import com.amaterek.ui.compose.navigation.destination.ReplaceCurrentDestination
import com.amaterek.ui.compose.navigation.destination.ScreenDestination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion

class Navigator(private val navController: NavController) {

    fun navigateTo(destination: Destination): Boolean {
        Log.d("Navigator", "navigateTo: $destination")
        return when (destination) {
            is DialogDestination -> {
                navController.navigate(destination.route)
                true
            }
            is ScreenDestination -> {
                navController.navigate(destination.route)
                true
            }
            is ControlDestinations -> navController.handleControlDestination(destination)
            else -> false
        }
    }

    private fun NavController.handleControlDestination(destination: ControlDestinations): Boolean =
        when (destination) {
            is PreviousDestination -> {
                popBackStack()
            }
            is PreviousDestinationWithResult<*> -> {
                setNavigationResult(
                    key = destination.key,
                    result = destination.result,
                )
                popBackStack()
            }
            is ReplaceCurrentDestination -> {
                currentDestinationRoute()?.let { currentRoute ->
                    navigate(route = destination.destination.route) {
                        popUpTo(route = currentRoute) {
                            inclusive = true
                        }
                    }
                    true
                } ?: false
            }
        }

    fun <T> resultFlow(destination: DestinationForResult<T>): Flow<T> {
        val currentBackStackEntry = requireNotNull(navController.currentBackStackEntry)
        val savedStateHandle = currentBackStackEntry.savedStateHandle
        return savedStateHandle.getLiveData<T>(destination.resultKey).asFlow().onCompletion {
            savedStateHandle.remove<T>(destination.resultKey)
        }
    }

    private fun <T> setNavigationResult(key: String, result: T) {
        val previousBackStackEntry = requireNotNull(navController.previousBackStackEntry)
        val savedStateHandle = previousBackStackEntry.savedStateHandle
        savedStateHandle[key] = result
    }
}

@Suppress("NOTHING_TO_INLINE")
private inline fun NavController.currentDestinationRoute() =
    currentBackStackEntry?.destination?.route

val LocalNavigator = staticCompositionLocalOf<Navigator> {
    throw IllegalStateException("Local navigator hasn't been provided")
}

fun Navigator.navigateBack() = navigateTo(PreviousDestination)

fun <T> Navigator.navigateBackWithResult(destination: DestinationForResult<T>, result: T) {
    // TODO Get rid of destination argument
    navigateTo(PreviousDestinationWithResult(destination.resultKey, result))
}