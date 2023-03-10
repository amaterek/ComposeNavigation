package com.amaterek.app.screen.forresult

import com.amaterek.ui.navigation.DestinationForResult
import com.amaterek.ui.navigation.compose.DestinationRouteWithBuilder
import com.amaterek.ui.navigation.compose.NavigationBuilder
import com.amaterek.ui.navigation.compose.addDestination
import com.amaterek.ui.navigation.destination.ScreenDestination
import com.amaterek.ui.navigation.destination.addArgument
import com.amaterek.ui.navigation.destination.addArgumentPlaceHolder

data class ScreenForResultDestination(val parameter: String) : ScreenDestination {

    override val route: String = BaseRoute.addArgument(parameter)

    companion object : DestinationRouteWithBuilder<String>, DestinationForResult<ScreenResult> {

        internal const val BaseRoute: String = "screen_for_result"

        override val route: String = BaseRoute.addArgumentPlaceHolder()

        override val resultKey: String = "screen_for_result_result"

        override fun build(builder: NavigationBuilder) {
            builder.addDestination(ScreenForResultDestination) { title ->
                ScreenForResult(title = title)
            }
        }
    }
}