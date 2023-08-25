package com.example.data_repository.repository

import com.example.data_repository.data_source.local.LocalLastComicNumberDataSource
import com.example.domain.repository.LastComicNumberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LastComicNumberRepositoryImpl @Inject constructor(
    private val localLastComicNumberDataSource: LocalLastComicNumberDataSource,
) : LastComicNumberRepository {
    override fun getLastComicNumber(): Flow<Long> =
        localLastComicNumberDataSource.getLastComicNumber()

    override suspend fun setLastComicNumber(num: Long) {
        localLastComicNumberDataSource.setLastComicNumber(num)
    }
}