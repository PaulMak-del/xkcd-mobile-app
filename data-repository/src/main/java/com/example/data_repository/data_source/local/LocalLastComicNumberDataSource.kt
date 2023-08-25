package com.example.data_repository.data_source.local

import kotlinx.coroutines.flow.Flow

interface LocalLastComicNumberDataSource {

    fun getLastComicNumber() : Flow<Long>

    suspend fun setLastComicNumber(num: Long)
}