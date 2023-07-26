package com.example.data_remote.source

import com.example.data_remote.networking.comic.ComicApiModel
import com.example.data_remote.networking.comic.ComicService
import com.example.data_repository.data_source.remote.RemoteComicDataSource
import com.example.domain.entity.Comic
import com.example.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteComicDataSourceImpl @Inject constructor(
    private val comicService: ComicService,
) : RemoteComicDataSource {

    override fun getComic(id: Long): Flow<Comic> = flow {
        emit(comicService.getComic(id))
    }.map { comicApiModel ->
        convert(comicApiModel)
    }.catch {
        throw UseCaseException.ComicException(it)
    }

    private fun convert(comicApiModel: ComicApiModel) =
        Comic(
            comicApiModel.id,
            comicApiModel.title,
            comicApiModel.img,
            comicApiModel.alt,
            false
    )
}