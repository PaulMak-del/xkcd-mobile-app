package com.example.data_local.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.example.data_repository.data_source.local.LocalLastComicNumberDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val LAST_COMIC_NUMBER = longPreferencesKey(name = "last_comic_number")

class LocalLastComicNumberDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalLastComicNumberDataSource {

    override fun getLastComicNumber() : Flow<Long> =
        dataStore.data
            .map { preferences ->
                preferences[LAST_COMIC_NUMBER] ?: 1L
            }


    override suspend fun setLastComicNumber(num: Long) {
        dataStore.edit { preferences ->
            preferences[LAST_COMIC_NUMBER] = num
        }
    }
}