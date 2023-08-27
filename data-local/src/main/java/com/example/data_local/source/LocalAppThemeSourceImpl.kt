package com.example.data_local.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.example.data_repository.data_source.local.LocalAppThemeSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val APP_THEME = intPreferencesKey(name = "app_theme")

class LocalAppThemeSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalAppThemeSource {

    override fun getAppTheme(): Flow<Int> =
        dataStore.data.map {  preferences ->
            preferences[APP_THEME] ?: 2
        }

    override suspend fun setAppTheme(themeState: Int) {
        dataStore.edit { preferences ->
            preferences[APP_THEME] = themeState
        }
    }
}