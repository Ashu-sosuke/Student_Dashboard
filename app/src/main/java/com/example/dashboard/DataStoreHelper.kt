// DataStoreHelper.kt
package com.example.dashboard

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object DataStoreHelper {
    private val Context.dataStore by preferencesDataStore(name = "settings")
    private val REMEMBER_ME_KEY = booleanPreferencesKey("remember_me")

    suspend fun saveRememberMe(context: Context, value: Boolean) {
        context.dataStore.edit { it[REMEMBER_ME_KEY] = value }
    }

    suspend fun getRememberMe(context: Context): Boolean {
        return context.dataStore.data.map { preferences ->
            preferences[REMEMBER_ME_KEY] ?: false
        }.first()
    }
}
