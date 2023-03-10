package com.amaterek.ui.navigation.destination

import androidx.compose.runtime.Immutable
import com.amaterek.ui.navigation.DestinationRoute

@Immutable
interface ScreenDestination : Destination {
    val route: String
}

fun ScreenDestination.replaceCurrent(): Destination =
    ReplaceCurrentDestination(
        destination = this,
    )

fun ScreenDestination.replaceBackStack(): Destination =
    ReplaceBackStackDestination(
        destination = this,
    )

fun ScreenDestination.popUpTo(
    destination: DestinationRoute<*>,
    inclusive: Boolean = false,
): Destination =
    PopUpToDestination(
        destination = this,
        popUpTo = destination,
        inclusive = inclusive,
    )