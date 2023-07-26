package com.example.data_repository.data_source.remote

import com.example.domain.entity.Comic
import kotlinx.coroutines.flow.Flow

interface RemoteComicDataSource {

    fun getComic(id: Long) : Flow<Comic>
}