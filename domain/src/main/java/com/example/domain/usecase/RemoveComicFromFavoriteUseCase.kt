package com.example.domain.usecase

import com.example.domain.entity.Comic
import com.example.domain.repository.ComicRepository
import javax.inject.Inject

class RemoveComicFromFavoriteUseCase @Inject constructor(
    private val comicRepository: ComicRepository,
) {

    fun execute(comic: Comic) {
        comicRepository.deleteComic(comic)
    }
}