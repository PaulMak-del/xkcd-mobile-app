package com.example.presentation_comic.comic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetComicUseCase
import com.example.presentation_common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val getComicUseCase: GetComicUseCase,
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
}