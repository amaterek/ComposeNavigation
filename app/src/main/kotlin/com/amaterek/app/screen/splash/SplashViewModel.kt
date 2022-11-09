package com.amaterek.app.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amaterek.util.compose.navigation.destination.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SplashViewModel @Inject constructor(
    private val navigationCallback: SplashNavigationCallback,
) : ViewModel() {

    private val navigationChannel = Channel<Destination>()
    val navigationEventFlow = navigationChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            navigationChannel.send(navigationCallback())
        }
    }
}