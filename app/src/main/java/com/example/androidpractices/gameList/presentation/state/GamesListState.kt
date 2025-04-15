package com.example.androidpractices.gameList.presentation.state

import com.example.androidpractices.gameList.domain.entity.GameShortEntity
import com.example.androidpractices.gameList.domain.entity.Platform

interface GamesListState {
    val query: String
    val items: List<GameShortEntity>
    val isEmpty: Boolean
    val isLoading: Boolean
    val error: String?
    val showTypesDialog: Boolean
    val typesVariants: Set<Platform>
    val selectedTypes: Set<Platform>
    val hasBadge: Boolean
}