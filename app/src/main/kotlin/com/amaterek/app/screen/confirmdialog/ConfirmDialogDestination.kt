package com.amaterek.app.screen.confirmdialog

import com.amaterek.util.compose.navigation.DestinationForResult
import com.amaterek.util.compose.navigation.DestinationRoute
import com.amaterek.util.compose.navigation.NavigationBuilder
import com.amaterek.util.compose.navigation.addDestination
import com.amaterek.util.compose.navigation.destination.DialogDestination

object ConfirmDialogDestination :
    DialogDestination, DestinationRoute<Unit>, DestinationForResult<Boolean> {
    override val route: String = "confirm_dialog"
    override val cancellable: Boolean = true
    override val resultKey: String = "confirm_dialog_result"

    override fun build(builder: NavigationBuilder) {
        builder.addDestination(ConfirmDialogDestination) { ConfirmDialog() }
    }
}