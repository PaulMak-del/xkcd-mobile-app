package com.example.data_remote.source

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.data_remote.networking.comic.ComicApiModel
import com.example.data_remote.networking.comic.ComicService
import com.example.data_repository.data_source.remote.RemoteComicDataSource
import com.example.domain.entity.Comic
import com.example.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.ByteArrayOutputStream
import java.net.URL
import javax.inject.Inject

class RemoteComicDataSourceImpl @Inject constructor(
    private val comicService: ComicService,
) : RemoteComicDataSource {

    override fun getComic(id: Long): Flow<Comic> = flow {
        emit(comicService.getComic(id))
    }.map { comicApiModel ->
        Log.d("ddd", "comicApiModel: {$comicApiModel}")
        convert(comicApiModel)
    }.catch {
        Log.d("ddd", "ComicException: $it")
        throw UseCaseException.ComicException(it)
    }

    private fun convert(comicApiModel: ComicApiModel) : Comic {
        return Comic(
            comicApiModel.id,
            comicApiModel.title,
            comicApiModel.imageUrlPath,
            comicApiModel.alt,
            false
        )
    }

    private fun getImageByteArrayFromUrl(urlPath: String) =
        try {
            val url = URL(urlPath)
            val bitmapImage = BitmapFactory.decodeStream(url.openStream())

            val stream = ByteArrayOutputStream()
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 90, stream)

            stream.toByteArray()
        } catch (e: Exception) {
            Log.d("ddd", "Can't get a image from url")

            null
        }
}