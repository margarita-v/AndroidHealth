package com.example.androidhealth.interactor

import android.app.UiModeManager
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private val Context.userSettingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")
private val darkThemeModeKey = intPreferencesKey("dark_theme_mode_key")

class UserSettingsInteractor(private val context: Context) {

    fun isDarkThemeEnabledBlocking(): Int =
        runBlocking {
            context.userSettingsDataStore.data.first().let {
                it[darkThemeModeKey] ?: UiModeManager.MODE_NIGHT_AUTO
            }
        }

    fun isDarkThemeEnabled(): Flow<Int> =
        context.userSettingsDataStore.data
            .map { it[darkThemeModeKey] ?: UiModeManager.MODE_NIGHT_AUTO }
            .flowOn(Dispatchers.Default)

    suspend fun setIsDarkThemeEnabled(value: Int) {
        AppCompatDelegate.setDefaultNightMode(value)
        withContext(Dispatchers.Default) {
            context.userSettingsDataStore.edit { it[darkThemeModeKey] = value }
        }
    }
}