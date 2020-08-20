package com.az.yagp.presentation.common

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

const val ACCESS_TOKEN = "access_token"

fun SharedPreferences.int(key: String? = null, default: Int = 0) =
        delegate(default, key, SharedPreferences::getInt, SharedPreferences.Editor::putInt)

fun SharedPreferences.string(key: String? = null, default: String = "") =
        delegate(default, key, SharedPreferences::getString, SharedPreferences.Editor::putString)

fun SharedPreferences.boolean(key: String? = null, default: Boolean = false) =
        delegate(default, key, SharedPreferences::getBoolean, SharedPreferences.Editor::putBoolean)


private inline fun <T> SharedPreferences.delegate(defaultValue: T, key: String?,
                                                  crossinline getter: SharedPreferences.(String, T) -> T,
                                                  crossinline setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor): ReadWriteProperty<Any, T> {
    return object : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>) =
                getter(key ?: property.name, defaultValue)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) =
                edit().setter(key ?: property.name, value).apply()
    }
}




