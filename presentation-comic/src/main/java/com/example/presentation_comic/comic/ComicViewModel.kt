package com.example.presentation_comic.comic

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

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val getComicUseCase: GetComicUseCase,
    private val insertComicUseCase: InsertComicUseCase,
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

    fun addComicToDB(comicModel: ComicModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val comic = Comic(comicModel.id, comicModel.title, comicModel.imageUrlPath, comicModel.alt, comicModel.isFavorite)
            insertComicUseCase.execute(InsertComicUseCase.Request(listOf(comic)))
        }
    }
}