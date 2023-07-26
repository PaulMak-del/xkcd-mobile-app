package com.example.data_repository.repository

import com.example.data_repository.data_source.local.LocalComicDataSource
import com.example.data_repository.data_source.remote.RemoteComicDataSource
import com.example.domain.entity.Comic
import com.example.domain.repository.ComicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ComicRepositoryImpl @Inject constructor(
    private val localComicDataSource: LocalComicDataSource,
    private val remoteComicDataStore: RemoteComicDataSource,
) : ComicRepository {

    override fun getComic(id: Long): Flow<Comic> =
        if (localComicDataSource.containComic(id)) localComicDataSource.getFavoriteComic(id)
        else remoteComicDataStore.getComic(id)

    override fun getFavoriteComics(): Flow<List<Comic>> =
        localComicDataSource.getFavoriteComics()
}