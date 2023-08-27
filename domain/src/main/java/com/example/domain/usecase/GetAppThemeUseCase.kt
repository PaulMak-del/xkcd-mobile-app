package com.example.domain.usecase

import com.example.domain.repository.AppThemeRepository
import javax.inject.Inject

class GetAppThemeUseCase @Inject constructor(
    private val appThemeRepository: AppThemeRepository,
) {

    fun execute() = appThemeRepository.getAppTheme()
}