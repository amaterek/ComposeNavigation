package com.amaterek.app.screen.forresult

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface ScreenResult : Parcelable {

    @Parcelize
    data class OptionSelected(val option: String) : ScreenResult

    @Parcelize
    object Cancelled : ScreenResult
}