package com.amaterek.ui.compose.navigation.destination

import com.amaterek.ui.compose.navigation.util.serializeToString

fun String.routeWithArgument(): String = "$this/{$ARGUMENT_NAME}"

fun <T : Any> String.routeForArgument(argument: T): String = "$this/${argument.serializeToString()}"

internal const val ARGUMENT_NAME = "args"