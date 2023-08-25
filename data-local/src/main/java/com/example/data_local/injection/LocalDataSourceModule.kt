package com.example.data_local.injection

import android.content.Context
import com.example.data_local.db.ComicDao
import com.example.data_local.source.LocalComicDataSourceImpl
import com.example.data_local.source.LocalLastComicNumberDataSourceImpl
import com.example.data_repository.data_source.local.LocalComicDataSource
import com.example.data_repository.data_source.local.LocalLastComicNumberDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Provides
    fun provideComicDataSource(comicDao: ComicDao) : LocalComicDataSource =
        LocalComicDataSourceImpl(comicDao)

    @Provides
    fun provideLocalLastComicNumberDataStore(@ApplicationContext context: Context) : LocalLastComicNumberDataSource =
        LocalLastComicNumberDataSourceImpl(context.dataStore)
}