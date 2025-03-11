package com.example.androidpractices.gameList.domain.entity

class GameShortEntity(
    val id: Int,
    val title: String,
    val tba: Boolean,
    val year: String,
    val image: String,
    val platforms: List<Platform>,
)