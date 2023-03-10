package com.amaterek.ui.navigation

import androidx.lifecycle.asFlow
import com.amaterek.ui.navigation.destination.Destination
import com.amaterek.ui.navigation.destination.PreviousDestination
import com.amaterek.ui.navigation.destination.PreviousDestinationWithResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion

interface Navigator {

    fun navigateTo(destination: Destination): Boolean

    fun canNavigateBack(): Boolean
}

fun Navigator.navigateBack() = navigateTo(PreviousDestination)

fun <T> Navigator.navigateBackWithResult(destination: DestinationForResult<T>, result: T) {
    // TODO Get rid of destination argument
    navigateTo(PreviousDestinationWithResult(destination.resultKey, result))
}

fun <T> Navigator.resultFlow(destination: DestinationForResult<T>): Flow<T> = when (this) {
    is JetpackNavigator -> resultFlow(destination)
    else -> error("Unsupported navigator to get result flow")
}

private fun <T> JetpackNavigator.resultFlow(destination: DestinationForResult<T>): Flow<T> {
    val currentBackStackEntry = requireNotNull(navController.currentBackStackEntry)
    val savedStateHandle = currentBackStackEntry.savedStateHandle
    return savedStateHandle.getLiveData<T>(destination.resultKey).asFlow().onCompletion {
        savedStateHandle.remove<T>(destination.resultKey)
    }
}