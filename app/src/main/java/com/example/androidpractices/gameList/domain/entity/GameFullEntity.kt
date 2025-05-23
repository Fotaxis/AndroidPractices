package com.example.androidpractices.gameList.domain.entity

class GameFullEntity(
    val id: String,
    val title: String,
    val description: String,
    val year: String,
    val tba: Boolean,
    val rating: List<Rating>,
    val image: String,
    val platforms: List<Platform>,
    val metacritic: Int,
    val developers: List<String>,
    val publishers: List<String>,
    val genres: List<String>,
    val tags: List<String>
)