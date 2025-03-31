package com.example.androidpractices.gameList.domain.repository

import com.example.androidpractices.gameList.domain.entity.GameFullEntity
import com.example.androidpractices.gameList.domain.entity.GameShortEntity

interface IGamesRepository {
    suspend fun getList(q: String = ""): List<GameShortEntity>
    suspend fun getById(id: String): GameFullEntity?
}