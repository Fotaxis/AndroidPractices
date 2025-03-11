package com.example.androidpractices.gameList.data.repository

import com.example.androidpractices.gameList.data.mock.GamesData
import com.example.androidpractices.gameList.domain.repository.IGamesRepository

class GamesRepository: IGamesRepository{
    override fun getList(q: String) = GamesData.gamesShort.filter { it.title.contains(q, ignoreCase = true) }
    override fun getById(id: Int) = GamesData.gamesFull.find { it.id == id }

}