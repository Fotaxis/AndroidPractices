package com.example.androidpractices.gameList.data.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.androidpractices.gameList.data.api.RawgIoApi
import com.example.androidpractices.gameList.data.database.GameDatabase
import com.example.androidpractices.gameList.data.entity.GameDbEntity
import com.example.androidpractices.gameList.data.mapper.GameResponseToEntityMapper
import com.example.androidpractices.gameList.domain.entity.GameShortEntity
import com.example.androidpractices.gameList.domain.entity.Platform
import com.example.androidpractices.gameList.domain.repository.IGamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GamesRepository(
    private val api: RawgIoApi,
    private val mapper: GameResponseToEntityMapper,
    private val db: GameDatabase

) : IGamesRepository {
    override suspend fun getList(q: String, filterTypes: Set<Platform>?): List<GameShortEntity> =
        withContext(Dispatchers.IO) {
            val response = api.getGames(q,
                platforms = filterTypes?.takeIf { it.isNotEmpty() }
                    ?.joinToString(",") { it.id.toString() })
            mapper.mapSearch(response)
        }

    override suspend fun getById(id: String) = withContext(Dispatchers.IO) {
        val response = api.getGame(id)
        mapper.mapFull(response)
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    override suspend fun saveFavorite(game: GameShortEntity) = withContext(Dispatchers.IO) {
        db.gameDao().insert(
            GameDbEntity(
                id = game.id.toLong(),
                title = game.title,
                platforms = game.platforms.joinToString(", ") { it.name },
                image = game.image
            )
        )
    }

    override suspend fun getFavorites(): List<GameShortEntity> = withContext(Dispatchers.IO) {
        db.gameDao().getAll().map {
            GameShortEntity(
                it.id.toString(),
                it.title.orEmpty(),
                it.image.orEmpty(),
                it.platforms?.split(", ")?.map { platform -> Platform(platform, -1) }?.toList()
                    .orEmpty()
            )
        }
    }


    override suspend fun removeFavorite(game: GameShortEntity) = withContext(Dispatchers.IO) {

        db.gameDao().delete(
            GameDbEntity(
                id = game.id.toLong(),
                title = game.title,
                platforms = game.platforms.joinToString(", ") { it.name },
                image = game.image
            )
        )
    }
}
