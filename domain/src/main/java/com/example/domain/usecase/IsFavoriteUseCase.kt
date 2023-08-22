package com.example.domain.usecase

import com.example.domain.repository.ComicRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val comicRepository: ComicRepository,
) {

    fun execute(comicId: Long): Boolean =
        comicRepository.containComic(comicId)
}