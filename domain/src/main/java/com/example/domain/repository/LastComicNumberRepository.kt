package com.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface LastComicNumberRepository {

    fun getLastComicNumber() : Flow<Long>

    suspend fun setLastComicNumber(num: Long)
}