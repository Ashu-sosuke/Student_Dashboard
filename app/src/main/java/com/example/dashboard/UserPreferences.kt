package com.example.dashboard


import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("user_prefs")

class UserPreferences(private val context: Context) {
    companion object {
        val REMEMBER_ME = booleanPreferencesKey("remember_me")
    }

    suspend fun saveRememberMe(value: Boolean) {
        context.dataStore.edit { it[REMEMBER_ME] = value }
    }

    suspend fun getRememberMe(): Boolean {
        return context.dataStore.data.map { it[REMEMBER_ME] ?: false }.first()
    }

    suspend fun clearRememberMe() {
        context.dataStore.edit { it.remove(REMEMBER_ME) }
    }
}
