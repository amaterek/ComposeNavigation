package com.amaterek.app.screen.confirmdialog

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amaterek.ui.navigation.LocalNavigator
import com.amaterek.ui.navigation.navigateBackWithResult

@Composable
internal fun ConfirmDialog() {
    val navigator = LocalNavigator.current

    fun navigateBackWithResult(result: Boolean) {
        navigator.navigateBackWithResult(ConfirmDialogDestination, result = result)
    }

    BackHandler {
        navigateBackWithResult(result = false)
    }

    fun confirm() {
        navigateBackWithResult(result = true)
    }

    fun cancel() {
        navigateBackWithResult(result = false)
    }

    Card(modifier = Modifier) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Confirm",
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Currently it is not possible to capture dismiss, " +
                        "so result can not be returned",
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = ::confirm
            ) {
                Text(text = "Ok")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = ::cancel
            ) {
                Text(text = "Cancel")
            }
        }
    }
}
