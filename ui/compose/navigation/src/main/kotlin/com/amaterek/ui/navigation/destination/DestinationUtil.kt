package com.amaterek.ui.navigation.destination

import android.util.Base64
import androidx.navigation.NavBackStackEntry
import com.google.gson.Gson
import kotlin.reflect.KClass

// TODO find better way to pass arguments
fun String.addArgumentPlaceHolder(): String = "$this/{$ARGUMENT_NAME}"

// TODO find better way to pass arguments
fun <T : Any> String.addArgument(argument: T): String = "$this/${argument.serialize().base64Encode()}"

internal fun <T : Any> NavBackStackEntry.getArgument(argumentType: KClass<T>): T? =
    arguments?.getString(ARGUMENT_NAME)?.base64Decode()?.deserialize(argumentType)

private fun Any.serialize(): String =
    Gson().toJson(this)

@Suppress("NOTHING_TO_INLINE")
private inline fun <T : Any> String.deserialize(type: KClass<T>): T =
    Gson().fromJson(this, type.java)

private fun String.base64Encode() =
    Base64.encodeToString(this.toByteArray(), Base64Flags)

private fun String.base64Decode() =
    String(Base64.decode(this, Base64Flags))

private const val Base64Flags = Base64.NO_WRAP or Base64.NO_PADDING

private const val ARGUMENT_NAME = "arg"