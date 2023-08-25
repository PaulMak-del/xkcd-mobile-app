package com.example.domain.usecase

import com.example.domain.repository.LastComicNumberRepository
import javax.inject.Inject

class SetLastComicNumberUseCase @Inject constructor(
    private val lastComicNumberRepository: LastComicNumberRepository,
) {

    suspend fun execute(num: Long) {
        lastComicNumberRepository.setLastComicNumber(num)
    }
}