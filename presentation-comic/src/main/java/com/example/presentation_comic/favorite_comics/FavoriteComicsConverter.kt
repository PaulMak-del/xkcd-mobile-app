package com.example.presentation_comic.favorite_comics

import com.example.domain.usecase.GetFavoriteComicsListUseCase
import com.example.presentation_common.state.CommonResultConverter
import javax.inject.Inject

class FavoriteComicsConverter @Inject constructor()
    : CommonResultConverter<GetFavoriteComicsListUseCase.Response, List<ComicPreviewModel>>() {

    override fun convertSuccess(data: GetFavoriteComicsListUseCase.Response): List<ComicPreviewModel> {
        return data.comics.map {
            ComicPreviewModel(
                it.id,
                it.title,
                it.imageUrlPath
            )
        }
    }
}