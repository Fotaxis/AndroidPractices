package com.example.androidpractices.gameList.presentation.state

import com.example.androidpractices.gameList.domain.entity.GameShortEntity

interface GamesListState {
    val query: String
    val items: List<GameShortEntity>
    val isEmpty: Boolean
}