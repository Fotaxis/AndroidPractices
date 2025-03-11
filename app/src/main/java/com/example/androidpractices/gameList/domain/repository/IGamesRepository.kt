package com.example.androidpractices.gameList.domain.repository

import com.example.androidpractices.gameList.domain.entity.GameFullEntity
import com.example.androidpractices.gameList.domain.entity.GameShortEntity

interface IGamesRepository {
    fun getList(q: String = ""): List<GameShortEntity>
    fun getById(id: Int): GameFullEntity?
}