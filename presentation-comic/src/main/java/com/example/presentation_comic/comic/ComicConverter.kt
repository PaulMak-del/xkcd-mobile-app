package com.example.presentation_comic.comic

import android.content.Context
import com.example.domain.usecase.GetComicUseCase
import com.example.presentation_common.state.CommonResultConverter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ComicConverter @Inject constructor(
    @ApplicationContext private val context: Context
) : CommonResultConverter<GetComicUseCase.Response, ComicModel>() {

    override fun convertSuccess(data: GetComicUseCase.Response): ComicModel {
        return ComicModel(
            data.comic.id,
            data.comic.title,
            data.comic.imageUrlPath,
            data.comic.alt
        )
    }
}