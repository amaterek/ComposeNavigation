package com.amaterek.app.screen.forresult

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
import com.amaterek.util.compose.navigation.LocalNavigator
import com.amaterek.util.compose.navigation.navigateBackWithResult

@Composable
internal fun ScreenForResult(title: String) {
    val navigator = LocalNavigator.current

    fun optionSelected(option: String) {
        navigator.navigateBackWithResult(
            ScreenForResultDestination,
            result = ScreenResult.OptionSelected(option)
        )
    }

    fun cancel() {
        navigator.navigateBackWithResult(
            ScreenForResultDestination,
            result = ScreenResult.Cancelled
        )
    }

    BackHandler(onBack = ::cancel)

    Card(modifier = Modifier) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { optionSelected("Option 1") }
            ) {
                Text(text = "Option 1")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { optionSelected("Option 2") }
            ) {
                Text(text = "Option 2")
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
