package com.example.presentation_comic.comic

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetComicUseCase
import com.example.domain.usecase.InsertComicUseCase
import com.example.presentation_common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.domain.entity.Comic
import com.example.domain.usecase.RemoveComicFromFavoriteUseCase

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val getComicUseCase: GetComicUseCase,
    private val insertComicUseCase: InsertComicUseCase,
    private val removeComicFromFavoriteUseCase: RemoveComicFromFavoriteUseCase,
    private val comicConverter: ComicConverter
) : ViewModel() {

    private val _comic : MutableStateFlow<UIState<ComicModel>> = MutableStateFlow(UIState.Loading)
    val comic : StateFlow<UIState<ComicModel>> = _comic

    fun loadComic(comicId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            getComicUseCase.execute(GetComicUseCase.Request(comicId))
                .map {
                    comicConverter.convert(it)
                }
                .collect {
                    _comic.value = it
                }
        }
    }

    fun addComicToFavorite(comicModel: ComicModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val comic = Comic(comicModel.id, comicModel.title, comicModel.imageUrlPath, comicModel.alt, comicModel.isFavorite)
            insertComicUseCase.execute(InsertComicUseCase.Request(listOf(comic)))
            Log.d("ddd", "add to DB")
            loadComic(comicModel.id)
            Log.d("ddd", "comic update")
        }
    }

    fun removeComicFromFavorite(comic: ComicModel) {
        viewModelScope.launch(Dispatchers.IO) {
            removeComicFromFavoriteUseCase.execute(Comic(
                id = comic.id,
                title = comic.title,
                imageUrlPath = comic.imageUrlPath,
                alt = comic.alt,
                isFavorite = comic.isFavorite
            ))
            Log.d("ddd", "remove from DB")
            loadComic(comic.id)
            Log.d("ddd", "comic update")
        }
    }
}