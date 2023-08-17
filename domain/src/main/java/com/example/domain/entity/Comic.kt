package com.example.domain.entity

data class Comic(
    val id: Long,
    val title: String,
    val imageUrlPath: String,
    val alt: String,
    val isFavorite: Boolean,
)