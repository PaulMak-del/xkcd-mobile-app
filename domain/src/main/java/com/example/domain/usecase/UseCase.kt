package com.example.domain.usecase

import kotlinx.coroutines.flow.map
import com.example.domain.entity.Result
import com.example.domain.entity.UseCaseException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException
import java.net.UnknownHostException

abstract class UseCase<I: UseCase.Request, O: UseCase.Response>(
    //private val configuration: Configuration
) {

    fun execute(request: I) = process(request)
        .map {
            Result.Success(it) as Result<O>
        }
        .flowOn(Dispatchers.IO)
        .catch {
            when (it.cause) {
                is UnknownHostException ->
                    emit(Result.UnknownHostError(UseCaseException.createFromThrowable(it)))
                is HttpException ->
                    emit(Result.NotFoundError(UseCaseException.createFromThrowable(it)))
                else ->
                    emit(Result.Error(UseCaseException.createFromThrowable(it)))
            }
        }

    internal abstract fun process(request: I): Flow<O>

    class Configuration(val dispatcher: CoroutineDispatcher)

    interface Request

    interface Response
}
