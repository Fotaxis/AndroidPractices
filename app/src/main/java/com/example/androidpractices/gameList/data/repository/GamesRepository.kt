package com.example.androidpractices.gameList.data.repository

import com.example.androidpractices.gameList.data.api.RawgIoApi
import com.example.androidpractices.gameList.data.mapper.GameResponseToEntityMapper
import com.example.androidpractices.gameList.domain.repository.IGamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GamesRepository(
    private val api: RawgIoApi,
    private val mapper: GameResponseToEntityMapper

) : IGamesRepository {
    override suspend fun getList(q: String) =
        withContext(Dispatchers.IO) {
            val response = api.getGames(q)
            mapper.mapSearch(response)
        }

    override suspend fun getById(id: String) =
        withContext(Dispatchers.IO) {
            val response = api.getGame(id)
            mapper.mapFull(response)
        }

}