package com.example.xkcd_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetAppThemeUseCase
import com.example.domain.usecase.SetAppThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAppThemeUseCase: GetAppThemeUseCase,
    private val setAppThemeUseCase: SetAppThemeUseCase,
) : ViewModel() {

    private val _appTheme: MutableStateFlow<Int> = MutableStateFlow(2)
    val appTheme: StateFlow<Int> = _appTheme

    fun loadAppTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            getAppThemeUseCase.execute().collect {
                _appTheme.value = it
            }
        }
    }

    fun setAppTheme(themeState: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _appTheme.value = themeState
            setAppThemeUseCase.execute(themeState)
        }
    }
}