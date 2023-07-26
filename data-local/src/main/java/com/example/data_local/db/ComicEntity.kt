package com.example.data_local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comic")
data class ComicEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "img") val img: String,
    @ColumnInfo(name = "alt") val alt: String,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean,
)