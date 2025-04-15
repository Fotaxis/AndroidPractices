package com.example.androidpractices.favorites.presentation.state

import com.example.androidpractices.gameList.domain.entity.GameShortEntity

data class FavoritesViewState(
    val items: List<GameShortEntity> = emptyList()
)
