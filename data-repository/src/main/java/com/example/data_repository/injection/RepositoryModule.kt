package com.example.data_repository.injection

import com.example.data_repository.repository.ComicRepositoryImpl
import com.example.domain.repository.ComicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLocalComicDataSource(comicRepository: ComicRepositoryImpl) : ComicRepository
}