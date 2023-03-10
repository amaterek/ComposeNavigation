package com.amaterek.ui.compose.navigation.util

import android.util.Base64
import com.google.gson.Gson
import kotlin.reflect.KClass

fun Any.serializeToString(): String =
    Base64.encodeToString(Gson().toJson(this).toByteArray(), Base64Flags)

inline fun <reified T : Any> String.deserializeParcelable(): T =
    deserializeParcelable(T::class)

@Suppress("NOTHING_TO_INLINE")
inline fun <T : Any> String.deserializeParcelable(type: KClass<T>): T =
    Gson().fromJson(String(Base64.decode(this, Base64Flags)), type.java)

const val Base64Flags = Base64.NO_WRAP or Base64.NO_PADDING