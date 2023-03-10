package com.amaterek.ui.navigation.destination

import com.amaterek.ui.navigation.DestinationRoute

internal sealed interface ControlDestinations : Destination

internal data class PreviousDestinationWithResult<T>(val key: String, val result: T) :
    ControlDestinations

internal data class ReplaceCurrentDestination(val destination: ScreenDestination) :
    ControlDestinations

internal data class ReplaceBackStackDestination(val destination: ScreenDestination) :
    ControlDestinations

internal data class PopUpToDestination(
    val destination: ScreenDestination,
    val popUpTo: DestinationRoute<*>,
    val inclusive: Boolean,
) : ControlDestinations