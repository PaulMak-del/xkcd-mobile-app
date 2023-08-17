package com.example.data_local.source

import com.example.data_local.db.ComicDao
import com.example.data_local.db.ComicEntity
import com.example.data_repository.data_source.local.LocalComicDataSource
import com.example.domain.entity.Comic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class LocalComicDataSourceImpl(
    private val comicDao: ComicDao
) : LocalComicDataSource {

    override fun getFavoriteComics(): Flow<List<Comic>> = flow {
        emit(comicDao.getFavoriteComics())
    }.map { comicEntities ->
        comicEntities.map {
            convert(it)
        }
    }

    override fun getFavoriteComic(id: Long): Flow<Comic> = flow {
        emit(comicDao.getFavoriteComic(id))
    }.map { comicEntity ->
        convert(comicEntity)
    }

    override fun containComic(id: Long): Boolean =
        comicDao.containComic(id)

    override fun insertComics(comics: List<Comic>) {
        comicDao.insertComic(comics.map {
            ComicEntity(
                it.id,
                it.title,
                it.imageUrlPath,
                it.alt,
            )
        })
    }

    override fun deleteComic(comic: Comic) = comicDao.deleteComic(
        ComicEntity(
            comic.id,
            comic.title,
            comic.imageUrlPath,
            comic.alt,
        )
    )

    private fun convert(comicEntity: ComicEntity) : Comic =
        Comic(
            comicEntity.id,
            comicEntity.title,
            comicEntity.imageUrlPath,
            comicEntity.alt,
            true
        )
}