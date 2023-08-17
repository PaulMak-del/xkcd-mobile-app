package com.example.data_local.injection

import com.example.data_local.db.ComicDao
import com.example.data_local.source.LocalComicDataSourceImpl
import com.example.data_repository.data_source.local.LocalComicDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Provides
    fun provideComicDataSource(comicDao: ComicDao) : LocalComicDataSource =
        LocalComicDataSourceImpl(comicDao)
}