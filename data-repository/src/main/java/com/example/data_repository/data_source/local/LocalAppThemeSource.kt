package com.example.data_repository.data_source.local

import kotlinx.coroutines.flow.Flow

interface LocalAppThemeSource {

    fun getAppTheme() : Flow<Int>

    suspend fun setAppTheme(themeState: Int)
}