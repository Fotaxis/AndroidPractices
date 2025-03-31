package com.example.androidpractices.gameList.domain.entity

class GameFullEntity(
    val id: Int,
    val title: String,
    val description: String,
    val year: String,
    val tba: Boolean,
    val rating: List<Rating>,
    val image: String,
    val platforms: List<Platform>,
    val metacritic: Int,
    val developers: List<String>,
    val publisher: String,
    val genres: List<Genre>,
    val tags: List<String>
)