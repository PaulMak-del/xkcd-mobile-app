package com.example.data_local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data_local.db.ComicDao
import com.example.data_local.db.ComicEntity

@Database(entities = [ComicEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun comicDao(): ComicDao
}