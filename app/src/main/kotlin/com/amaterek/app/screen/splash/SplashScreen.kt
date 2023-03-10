package com.amaterek.app.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.amaterek.ui.compose.navigation.LocalNavigator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
internal fun SplashScreen() {
    val viewModel = hiltViewModel<SplashViewModel>()
    val navigator = LocalNavigator.current

    LaunchedEffect(viewModel) {
        viewModel.navigationEventFlow
            .onEach { navigator.navigateTo(it) }
            .launchIn(this)
    }

    Surface(modifier =  Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Splash",
                style = MaterialTheme.typography.h4
            )
        }
    }
}
