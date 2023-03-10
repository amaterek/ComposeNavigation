package com.amaterek.app.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amaterek.app.screen.confirmdialog.ConfirmDialogDestination
import com.amaterek.app.screen.forresult.ScreenForResultDestination
import com.amaterek.ui.navigation.destination.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor() : ViewModel() {

    sealed interface State {
        object Idle : State
    }

    private val _stateFlow = MutableStateFlow<State>(State.Idle)
    val stateFlow = _stateFlow.asStateFlow()

    private val navigationChannel = Channel<Destination>()
    val navigationEventFlow = navigationChannel.receiveAsFlow()

    fun confirmRequested() {
        viewModelScope.launch { navigationChannel.send(ConfirmDialogDestination) }
    }

    fun screenForResultRequested(parameter: String) {
        viewModelScope.launch {
            navigationChannel.send(ScreenForResultDestination(parameter))
        }
    }
}