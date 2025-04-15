package com.example.androidpractices.gameList.domain.repository

import com.example.androidpractices.gameList.domain.entity.GameFullEntity
import com.example.androidpractices.gameList.domain.entity.GameShortEntity
import com.example.androidpractices.gameList.domain.entity.Platform

interface IGamesRepository {
    suspend fun getList(q: String = "", filterTypes: Set<Platform>? = null): List<GameShortEntity>
    suspend fun getById(id: String): GameFullEntity?
    suspend fun getFavorites(): List<GameShortEntity>
    suspend fun saveFavorite(game: GameShortEntity)
    suspend fun removeFavorite(game: GameShortEntity)
}