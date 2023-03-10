package com.amaterek.ui.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.amaterek.ui.compose.navigation.destination.ARGUMENT_NAME
import com.amaterek.ui.compose.navigation.destination.DialogDestination
import com.amaterek.ui.compose.navigation.destination.ScreenDestination
import com.amaterek.ui.compose.navigation.util.deserializeParcelable
import kotlin.reflect.KClass

class NavigationBuilder(
    private val navGraphBuilder: NavGraphBuilder,
) {

    fun <T : Any> addDestinationSpec(
        destination: DestinationRoute<T>,
        argumentType: KClass<T>,
        content: @Composable (T) -> Unit,
    ) {
        when (destination) {
            is DialogDestination ->
                navGraphBuilder.dialog(
                    route = destination.route,
                    dialogProperties = DialogProperties(
                        dismissOnBackPress = destination.cancellable,
                        dismissOnClickOutside = destination.cancellable,
                    ),
                    content = {
                        val argument = it.getArgument(argumentType) ?: Unit
                        @Suppress("UNCHECKED_CAST")
                        content(argument as T)
                    }
                )
            is ScreenDestination ->
                navGraphBuilder.composable(
                    route = destination.route,
                    content = {
                        val argument = it.getArgument(argumentType) ?: Unit
                        @Suppress("UNCHECKED_CAST")
                        content(argument as T)
                    }
                )
        }
    }
}

inline fun <reified T : Any> NavigationBuilder.addDestination(
    destination: DestinationRoute<T>,
    noinline content: @Composable (T) -> Unit,
) = addDestinationSpec(destination, T::class, content)

private fun <T : Any> NavBackStackEntry.getArgument(argumentType: KClass<T>): T? =
    arguments?.getString(ARGUMENT_NAME)?.deserializeParcelable(argumentType)