package com.example.presentation_comic.favorite_comics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetFavoriteComicsListUseCase
import com.example.presentation_common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteComicsViewModel @Inject constructor(
    private val getFavoriteComicsListUseCase: GetFavoriteComicsListUseCase,
    private val favoriteComicsConverter: FavoriteComicsConverter,
) : ViewModel() {

    private val _favoriteComicsList: MutableStateFlow<UIState<List<ComicPreviewModel>>> =
        MutableStateFlow(UIState.Loading)
    val favoriteComicsList: StateFlow<UIState<List<ComicPreviewModel>>> = _favoriteComicsList

    fun loadFavoriteComicsList() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteComicsListUseCase.execute(GetFavoriteComicsListUseCase.Request)
                .map {
                    favoriteComicsConverter.convert(it)
                }
                .collect {
                    _favoriteComicsList.value = it
                }
        }
    }
}