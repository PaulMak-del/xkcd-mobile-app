package com.example.data_remote.networking.comic

import com.squareup.moshi.Json

data class ComicApiModel(
    @Json(name = "num") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "img") val img: String,
    @Json(name = "alt") val alt: String,
)
