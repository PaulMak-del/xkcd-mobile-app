package com.example.presentation_common.state

import com.example.domain.entity.Result

abstract class CommonResultConverter<T: Any, R: Any> {

    fun convert(result: Result<T>) : UIState<R> {
        return when (result) {
            is Result.Error -> {
                UIState.Error(result.exception.localizedMessage.orEmpty())
            }
            is Result.Success -> {
                UIState.Success(convertSuccess(result.data))
            }
            is Result.UnknownHostError -> {
                UIState.UnknownHostError(result.exception.localizedMessage.orEmpty())
            }
            is Result.NotFoundError -> {
                UIState.NotFoundError(result.exception.localizedMessage.orEmpty())
            }
        }
    }

    abstract fun convertSuccess(data: T): R
}