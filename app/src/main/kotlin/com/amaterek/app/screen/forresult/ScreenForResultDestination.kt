package com.amaterek.app.screen.forresult

import com.amaterek.ui.compose.navigation.DestinationForResult
import com.amaterek.ui.compose.navigation.NavigationBuilder
import com.amaterek.ui.compose.navigation.DestinationRouteWithBuilder
import com.amaterek.ui.compose.navigation.addDestination
import com.amaterek.ui.compose.navigation.destination.ScreenDestination
import com.amaterek.ui.compose.navigation.destination.routeForArgument
import com.amaterek.ui.compose.navigation.destination.routeWithArgument

data class ScreenForResultDestination(val parameter: String) : ScreenDestination {
    override val route: String = BaseRoute.routeForArgument(parameter)

    companion object ScreenForResultDestination :
        ScreenDestination, DestinationRouteWithBuilder<String>, DestinationForResult<ScreenResult> {
        const val BaseRoute: String = "screen_for_result"
        override val route: String = BaseRoute.routeWithArgument()

        override val resultKey: String = "screen_for_result_result"

        override fun build(builder: NavigationBuilder) {
            builder.addDestination(ScreenForResultDestination) { title ->
                ScreenForResult(title = title)
            }
        }
    }
}