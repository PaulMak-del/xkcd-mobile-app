package com.example.data_remote.injection

import com.example.data_remote.source.RemoteComicDataSourceImpl
import com.example.data_repository.data_source.remote.RemoteComicDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindRemoteComicDataSource(remoteComicDataSourceImpl: RemoteComicDataSourceImpl) : RemoteComicDataSource
}