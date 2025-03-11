package com.example.androidpractices.gameList.presentation.state

import com.example.androidpractices.gameList.domain.entity.GameFullEntity

interface GameDetailState {
    val game: GameFullEntity?
    val rating: Float
    val isRatingVisible: Boolean
}