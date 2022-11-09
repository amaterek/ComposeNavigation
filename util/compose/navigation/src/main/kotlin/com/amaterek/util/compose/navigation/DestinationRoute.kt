package com.amaterek.util.compose.navigation

interface DestinationRoute<ArgumentType> {

    val route: String

    fun build(builder: NavigationBuilder)
}