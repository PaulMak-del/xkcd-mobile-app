package com.example.data_remote.networking.comic

import retrofit2.http.GET
import retrofit2.http.Path

interface ComicService {

    @GET("/{comicNum}/info.0.json")
    suspend fun getComic(@Path("comicNum") id: Long): ComicApiModel
}