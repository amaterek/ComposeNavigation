package com.amaterek.util.compose.navigation.destination

sealed interface ControlDestinations : Destination

object PreviousDestination : ControlDestinations

data class PreviousDestinationWithResult<T>(val key: String, val result: T) : ControlDestinations

data class ReplaceCurrentDestination(val destination: ScreenDestination) : ControlDestinations
