package com.example.presentation_comic.comic

import com.example.domain.usecase.GetComicUseCase
import com.example.presentation_common.state.CommonResultConverter
import javax.inject.Inject

class ComicConverter @Inject constructor(
) : CommonResultConverter<GetComicUseCase.Response, ComicModel>() {

    override fun convertSuccess(data: GetComicUseCase.Response): ComicModel {
        return ComicModel(
            data.comic.id,
            data.comic.title,
            data.comic.imageUrlPath,
            data.comic.alt,
            data.comic.isFavorite
        )
    }
}