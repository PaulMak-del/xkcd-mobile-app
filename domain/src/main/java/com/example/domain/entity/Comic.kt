package com.example.domain.entity

data class Comic(
    val id: Long,
    val title: String,
    val img: String,
    val alt: String,
    var isFavorite: Boolean,
)