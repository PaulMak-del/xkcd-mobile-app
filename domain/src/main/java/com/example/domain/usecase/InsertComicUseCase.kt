package com.example.domain.usecase

import com.example.domain.entity.Comic
import com.example.domain.repository.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertComicUseCase @Inject constructor(
    private val comicRepository: ComicRepository,
) : UseCase<InsertComicUseCase.Request, InsertComicUseCase.Response>() {

    override fun process(request: Request): Flow<Response> {
        comicRepository.insertComic(request.comics)

        return flow { Response() }
    }

    data class Request(val comics: List<Comic>) : UseCase.Request

    class Response : UseCase.Response

}