package com.example.domain.usecase

import com.example.domain.repository.LastComicNumberRepository
import javax.inject.Inject

class GetLastComicNumberUseCase @Inject constructor(
    private val lastComicNumberRepository: LastComicNumberRepository,
) {

    fun execute() = lastComicNumberRepository.getLastComicNumber()
}