package com.amaterek.ui.compose.navigation

interface DestinationRoute<ArgumentType> {

    val route: String

    fun build(builder: NavigationBuilder)
}