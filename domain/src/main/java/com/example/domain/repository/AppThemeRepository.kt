package com.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface AppThemeRepository {

    fun getAppTheme() : Flow<Int>

    suspend fun setAppTheme(themeState: Int)
}