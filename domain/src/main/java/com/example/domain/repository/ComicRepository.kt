package com.example.domain.repository

import com.example.domain.entity.Comic
import kotlinx.coroutines.flow.Flow

interface ComicRepository {

    fun getComic(id: Long) : Flow<Comic>

    fun getFavoriteComics() : Flow<List<Comic>>
}