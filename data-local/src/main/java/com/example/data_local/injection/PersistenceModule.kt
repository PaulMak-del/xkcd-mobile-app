package com.example.data_local.injection

import android.content.Context
import androidx.room.Room
import com.example.data_local.AppDatabase
import com.example.data_local.db.ComicDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()

    @Provides
    fun provideComicDao(db: AppDatabase) : ComicDao = db.comicDao()
}