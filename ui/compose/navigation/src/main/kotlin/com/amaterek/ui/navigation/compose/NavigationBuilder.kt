package com.amaterek.ui.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.amaterek.ui.navigation.DestinationRoute
import com.amaterek.ui.navigation.destination.DialogDestination
import com.amaterek.ui.navigation.destination.getArgument
import kotlin.reflect.KClass

class NavigationBuilder(
    private val navGraphBuilder: NavGraphBuilder,
) {

    fun <T : Any> addDestinationSpec(
        destinationRoute: DestinationRoute<T>,
        argumentType: KClass<T>,
        content: @Composable (T) -> Unit,
    ) {
        when (destinationRoute) {
            is DialogDestination ->
                navGraphBuilder.dialog(
                    route = destinationRoute.route,
                    dialogProperties = DialogProperties(
                        dismissOnBackPress = destinationRoute.cancellable,
                        dismissOnClickOutside = destinationRoute.cancellable,
                    ),
                    content = {
                        val argument = it.getArgument(argumentType) ?: Unit
                        @Suppress("UNCHECKED_CAST")
                        content(argument as T)
                    }
                )
            else -> navGraphBuilder.composable(
                route = destinationRoute.route,
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