package com.amaterek.ui.compose.navigation

interface DestinationRouteWithBuilder<ArgumentType>: DestinationRoute<ArgumentType> {

    fun build(builder: NavigationBuilder)
}