package com.example.data_local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ComicDao {

    @Query("select * from comic")
    fun getFavoriteComics() : List<ComicEntity>

    @Query("select * from comic where id = :id")
    fun getFavoriteComic(id: Long) : ComicEntity

    @Query("select exists(select * from comic where id = :id)")
    fun containComic(id: Long) : Boolean

    @Insert
    fun insertComic(comics: List<ComicEntity>)

    @Delete
    fun deleteComic(comic: ComicEntity)
}