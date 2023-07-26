package com.example.domain.usecase

import com.example.domain.entity.Comic
import com.example.domain.repository.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteComicsListUseCase @Inject constructor(
    val configuration: UseCase.Configuration,
    private val comicRepository: ComicRepository
    ) : UseCase<GetFavoriteComicsListUseCase.Request, GetFavoriteComicsListUseCase.Response>(configuration) {


    override fun process(request: Request): Flow<Response> =
        comicRepository.getFavoriteComics()
            .map {
                Response(it)
            }

    object Request :  UseCase.Request
    data class Response(val comics: List<Comic>) : UseCase.Response
}