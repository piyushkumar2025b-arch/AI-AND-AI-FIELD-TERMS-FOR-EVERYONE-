package com.example.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserPreferencesRepository(private val context: Context) {
    companion object {
        val KEY_THEME_MODE = stringPreferencesKey("theme_mode") // "SYSTEM", "LIGHT", "DARK"
        val KEY_OPENROUTER_API_KEY = stringPreferencesKey("openrouter_api_key")
        val KEY_OPENROUTER_MODEL = stringPreferencesKey("openrouter_model")
        val KEY_AUTO_KEEP_AI_IN_APP = booleanPreferencesKey("auto_keep_ai_in_app")
        val KEY_DB_INITIALIZED = booleanPreferencesKey("db_initialized_v4")
    }

    val themeModeFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[KEY_THEME_MODE] ?: "SYSTEM"
    }

    val openRouterApiKeyFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[KEY_OPENROUTER_API_KEY] ?: ""
    }

    val openRouterModelFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[KEY_OPENROUTER_MODEL] ?: "openrouter/free"
    }

    val autoKeepAiInAppFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_AUTO_KEEP_AI_IN_APP] ?: true // Default to true so user AI insights are kept safely!
    }

    val isDbInitializedFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_DB_INITIALIZED] ?: false
    }

    suspend fun setThemeMode(mode: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_THEME_MODE] = mode
        }
    }

    suspend fun setOpenRouterApiKey(apiKey: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_OPENROUTER_API_KEY] = apiKey.trim()
        }
    }

    suspend fun setOpenRouterModel(model: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_OPENROUTER_MODEL] = model.trim()
        }
    }

    suspend fun setAutoKeepAiInApp(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_AUTO_KEEP_AI_IN_APP] = enabled
        }
    }

    suspend fun setDbInitialized(initialized: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_DB_INITIALIZED] = initialized
        }
    }
}
