package com.example.presentation_comic.comic

import android.content.Context
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
import com.example.domain.usecase.GetLastComicNumberUseCase
import com.example.domain.usecase.RemoveComicFromFavoriteUseCase
import com.example.domain.usecase.SetLastComicNumberUseCase
import com.example.presentation_comic.ShareImageFromUrl

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val getComicUseCase: GetComicUseCase,
    private val insertComicUseCase: InsertComicUseCase,
    private val removeComicFromFavoriteUseCase: RemoveComicFromFavoriteUseCase,
    private val getLastComicNumberUseCase: GetLastComicNumberUseCase,
    private val setLastComicNumberUseCase: SetLastComicNumberUseCase,
    private val comicConverter: ComicConverter,
    private val shareImageFromUrl: ShareImageFromUrl,
) : ViewModel() {

    private val _comic: MutableStateFlow<UIState<ComicModel>> = MutableStateFlow(UIState.Loading)
    val comic: StateFlow<UIState<ComicModel>> = _comic

    private val _lastComicNumber: MutableStateFlow<Long> = MutableStateFlow(1L)
    val lastComicNumber: StateFlow<Long> = _lastComicNumber

    /**
     * Load lastComicNumber and Comic before change state from `Loading` to `Success`
     */
    fun preloadComic() {
        Log.d("ddd", "[preloadComic] {start}")
        viewModelScope.launch(Dispatchers.IO) {
            getLastComicNumberUseCase.execute().collect {
                _lastComicNumber.value = it

                getComicUseCase.execute(GetComicUseCase.Request(it))
                    .map { result ->
                        comicConverter.convert(result)
                    }
                    .collect { uiState ->
                        _comic.value = uiState
                        //Log.d("ddd", "loadComic: ${_comic.value}")
                        Log.d("ddd", "[preloadComic] {end}")
                    }
            }
        }
    }

    fun loadComic(comicId: Long) {
        Log.d("ddd", "[loadComic] {start}")
        viewModelScope.launch(Dispatchers.IO) {
            getComicUseCase.execute(GetComicUseCase.Request(comicId))
                .map {
                    comicConverter.convert(it)
                }
                .collect {
                    _comic.value = it
                    //Log.d("ddd", "loadComic: ${_comic.value}")
                }
            Log.d("ddd", "[loadComic] {end}")
        }
    }

    fun addComicToFavorite(comicModel: ComicModel) {
        Log.d("ddd", "[addComicToFavorite] {start}")
        viewModelScope.launch(Dispatchers.IO) {
            val comic = Comic(
                comicModel.id,
                comicModel.title,
                comicModel.imageUrlPath,
                comicModel.alt,
                comicModel.isFavorite
            )
            insertComicUseCase.execute(InsertComicUseCase.Request(listOf(comic)))
            Log.d("ddd", "[addComicToFavorite] {end}")
            loadComic(comicModel.id)
        }
    }

    fun removeComicFromFavorite(comic: ComicModel) {
        Log.d("ddd", "[removeComicFromFavorite] {start}")
        viewModelScope.launch(Dispatchers.IO) {
            removeComicFromFavoriteUseCase.execute(
                Comic(
                    id = comic.id,
                    title = comic.title,
                    imageUrlPath = comic.imageUrlPath,
                    alt = comic.alt,
                    isFavorite = comic.isFavorite
                )
            )
            Log.d("ddd", "[removeComicFromFavorite] {end}")
            loadComic(comic.id)
        }
    }

    fun shareComicByUrl(context: Context, imageUrlPath: String) {
        Log.d("ddd", "[shareComicByUrl] {start}")
        viewModelScope.launch(Dispatchers.IO) {
            shareImageFromUrl.share(context, imageUrlPath)
            Log.d("ddd", "[shareComicByUrl] {end}")
        }
    }

    fun loadLastComicNumber() {
        Log.d("ddd", "[loadLastComicNumber] {start}")
        viewModelScope.launch(Dispatchers.IO) {
            getLastComicNumberUseCase.execute().collect {
                _lastComicNumber.value = it
                Log.d("ddd", "[loadLastComicNumber] {end} : _lastComicNumber = $it")
            }
        }
    }

    fun setLastComicNumber(num: Long) {
        Log.d("ddd", "[setLastComicNumber] {start} : num = $num")
        viewModelScope.launch(Dispatchers.IO) {
            setLastComicNumberUseCase.execute(num)
            Log.d("ddd", "[setLastComicNumber] {end} : num = $num")
            // loadComic(num)
        }
    }
}






