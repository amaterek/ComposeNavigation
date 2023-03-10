package com.amaterek.ui.navigation.destination

import androidx.compose.runtime.Immutable

@Immutable
internal data class ExternalDestination(val external: Destination) : Destination

fun Destination.toExternalNavigator(): Destination =
    ExternalDestination(this)