package com.amaterek.app.screen.confirmdialog

import com.amaterek.ui.navigation.DestinationForResult
import com.amaterek.ui.navigation.compose.DestinationRouteWithBuilder
import com.amaterek.ui.navigation.compose.NavigationBuilder
import com.amaterek.ui.navigation.compose.addDestination
import com.amaterek.ui.navigation.destination.DialogDestination

object ConfirmDialogDestination :
    DialogDestination, DestinationRouteWithBuilder<Unit>, DestinationForResult<Boolean> {
    override val route: String = "confirm_dialog"
    override val cancellable: Boolean = false
    override val resultKey: String = "confirm_dialog_result"

    override fun build(builder: NavigationBuilder) {
        builder.addDestination(ConfirmDialogDestination) { ConfirmDialog() }
    }
}