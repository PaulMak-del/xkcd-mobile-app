package com.example.presentation_common.state

sealed class UIState<out T : Any> {
    object Loading : UIState<Nothing>()

    data class Error(val errorMessage: String) : UIState<Nothing>()

    data class UnknownHostError(val errorMessage: String) : UIState<Nothing>()

    data class NotFoundError(val errorMessage: String) : UIState<Nothing>()

    data class Success<T : Any>(val data: T) : UIState<T>()
}