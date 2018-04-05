package com.grgt.learnkotlin.extensions

import android.annotation.SuppressLint
import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by JDRJ on 2017/12/27.
 */
object DelegatesExt {
    fun <T> notNullSingleValue(): ReadWriteProperty<Any?, T> = NotNullSingleValueVar()

    fun <T> preference(context: Context, name: String, default: T) = Preference(context, name, default)
}
private class NotNullSingleValueVar<T>() : ReadWriteProperty<Any?, T> {

    private var value: T? = null
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("${property.name} not initialized")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (value == null) value else throw IllegalStateException("${property.name} already initialized")
    }

}

class Preference<T>(val context: Context, val name: String, val default: T) : ReadWriteProperty<Any?, T>{
    val prefs by lazy { context.getSharedPreferences("default", Context.MODE_PRIVATE) }
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> findPreference(name: String, default: T): T = with(prefs) {
        val res: Any = when(default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can not be save into preference")
        }

        res as T
    }

    @SuppressLint("CommitPrefEdits")
    private fun <T> putPreference(name: String, default: T) = with(prefs.edit()) {
        when(default) {
            is Long -> putLong(name, default)
            is String -> putString(name, default)
            is Int -> putInt(name, default)
            is Boolean -> putBoolean(name, default)
            is Float -> putFloat(name, default)
            else -> throw IllegalArgumentException("This type can not be save into preference")
        }.apply()
    }


}