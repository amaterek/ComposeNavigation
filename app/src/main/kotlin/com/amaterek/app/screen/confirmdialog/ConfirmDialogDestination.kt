package com.amaterek.app.screen.confirmdialog

import com.amaterek.ui.compose.navigation.DestinationForResult
import com.amaterek.ui.compose.navigation.NavigationBuilder
import com.amaterek.ui.compose.navigation.DestinationRouteWithBuilder
import com.amaterek.ui.compose.navigation.addDestination
import com.amaterek.ui.compose.navigation.destination.DialogDestination

object ConfirmDialogDestination :
    DialogDestination, DestinationRouteWithBuilder<Unit>, DestinationForResult<Boolean> {
    override val route: String = "confirm_dialog"
    override val cancellable: Boolean = true
    override val resultKey: String = "confirm_dialog_result"

    override fun build(builder: NavigationBuilder) {
        builder.addDestination(ConfirmDialogDestination) { ConfirmDialog() }
    }
}