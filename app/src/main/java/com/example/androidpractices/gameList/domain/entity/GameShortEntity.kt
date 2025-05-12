package com.example.androidpractices.gameList.domain.entity

class GameShortEntity(
    val id: String,
    val title: String,
    val image: String,
    val platforms: List<Platform>,
)