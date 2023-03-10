package com.amaterek.ui.navigation.compose

import com.amaterek.ui.navigation.DestinationRoute

interface DestinationRouteWithBuilder<ParamType>: DestinationRoute<ParamType> {

    fun build(builder: NavigationBuilder)
}