package com.example.androidpractices.gameList.data.mapper

import com.example.androidpractices.gameList.data.model.GameFullResponse
import com.example.androidpractices.gameList.data.model.GamesSearchResponse
import com.example.androidpractices.gameList.domain.entity.GameFullEntity
import com.example.androidpractices.gameList.domain.entity.GameShortEntity
import com.example.androidpractices.gameList.domain.entity.Genre
import com.example.androidpractices.gameList.domain.entity.Platform
import com.example.androidpractices.gameList.domain.entity.Rating

class GameResponseToEntityMapper {
    fun mapSearch(response: GamesSearchResponse) =
        response.results?.map { game ->
            GameShortEntity(
                id = game.id.orEmpty(),
                title = game.title.orEmpty(),
                image = game.image.orEmpty(),
                platforms = game.platforms?.map {
                    Platform(it.platform.name.orEmpty())
                }.orEmpty(),
            )
        }.orEmpty()

    fun mapFull(response: GameFullResponse) =
        GameFullEntity(
            id = response.id.orEmpty(),
            title = response.title.orEmpty(),
            description = response.description.orEmpty(),
            year = response.released?.substringBefore("-").orEmpty(),
            tba = response.tba ?: true,
            rating = response.ratings?.map {
                Rating(
                    metascore = it.metascore ?: 0,
                    platform = Platform(it.platform?.name.orEmpty())
                )
            }.orEmpty(),
            image = response.image.orEmpty(),
            platforms = response.platforms?.map {
                Platform(it.platform.name.orEmpty())
            }.orEmpty(),
            metacritic = response.metacritic ?: 0,
            developers = response.developers?.map {
                it.name.orEmpty()
            }.orEmpty(),
            publishers = response.publishers?.map {
                it.name.orEmpty()
            }.orEmpty(),
            genres = response.genres?.map {
                Genre(it.name.orEmpty())
            }.orEmpty(),
            tags = response.tags?.map {
                it.name.orEmpty()
            }.orEmpty()
        )
}