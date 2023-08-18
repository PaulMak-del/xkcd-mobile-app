package com.example.domain.usecase

import com.example.domain.entity.Comic
import com.example.domain.repository.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetComicUseCase @Inject constructor(
    private val comicRepository: ComicRepository,
) : UseCase<GetComicUseCase.Request, GetComicUseCase.Response>() {

    override fun process(request: GetComicUseCase.Request): Flow<GetComicUseCase.Response> =
        comicRepository.getComic(request.comicId)
            .map {
                Response(it)
            }

    data class Request(val comicId: Long) : UseCase.Request
    data class Response(val comic: Comic) : UseCase.Response
}