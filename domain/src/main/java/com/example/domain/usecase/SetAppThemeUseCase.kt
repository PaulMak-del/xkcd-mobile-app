package com.example.domain.usecase

import com.example.domain.repository.AppThemeRepository
import javax.inject.Inject

class SetAppThemeUseCase @Inject constructor(
    private val appThemeRepository: AppThemeRepository,
) {

    suspend fun execute(themeState: Int) {
        appThemeRepository.setAppTheme(themeState)
    }
}