package com.amaterek.app.screen.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.amaterek.app.screen.confirmdialog.ConfirmDialogDestination
import com.amaterek.app.screen.forresult.ScreenForResultDestination
import com.amaterek.util.compose.navigation.LocalNavigator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
internal fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val navigator = LocalNavigator.current
    val context = LocalContext.current.applicationContext

    LaunchedEffect(viewModel) {
        viewModel.navigationEventFlow
            .onEach { navigator.navigateTo(it) }
            .launchIn(this)
    }

    LaunchedEffect(viewModel) {
        navigator.resultFlow(ConfirmDialogDestination).onEach {
            Toast.makeText(context, "Confirm result is $it", Toast.LENGTH_SHORT).show()
        }.launchIn(this)
    }

    LaunchedEffect(viewModel) {
        navigator.resultFlow(ScreenForResultDestination).onEach {
            Toast.makeText(context, "Screen result is ${it::class.simpleName}", Toast.LENGTH_SHORT)
                .show()
        }.launchIn(this)
    }

    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Home",
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.confirmRequested() }
            ) {
                Text(text = "Confirm dialog")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.screenForResultRequested("Passed title") }
            ) {
                Text(text = "Screen for result with parameter")
            }
        }
    }
}
