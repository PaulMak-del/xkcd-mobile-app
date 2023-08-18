package com.example.presentation_comic.comic

data class ComicModel(
    val id: Long,
    val title: String,
    val imageUrlPath: String,
    val alt: String,
    val isFavorite: Boolean
)