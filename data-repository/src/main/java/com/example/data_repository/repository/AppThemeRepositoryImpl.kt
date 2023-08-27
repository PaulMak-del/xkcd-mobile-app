package com.example.data_repository.repository

import com.example.data_repository.data_source.local.LocalAppThemeSource
import com.example.domain.repository.AppThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppThemeRepositoryImpl @Inject constructor(
    private val localAppThemeSource: LocalAppThemeSource,
) : AppThemeRepository {

    override fun getAppTheme(): Flow<Int> = localAppThemeSource.getAppTheme()

    override suspend fun setAppTheme(themeState: Int) {
        localAppThemeSource.setAppTheme(themeState)
    }
}