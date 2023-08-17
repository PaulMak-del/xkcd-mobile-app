package com.example.data_repository.data_source.local

import com.example.domain.entity.Comic
import kotlinx.coroutines.flow.Flow


interface LocalComicDataSource {

    fun getFavoriteComics(): Flow<List<Comic>>

    fun getFavoriteComic(id: Long): Flow<Comic>

    fun containComic(id: Long) : Boolean

    fun insertComics(comics: List<Comic>)

    fun deleteComic(comic: Comic)
}