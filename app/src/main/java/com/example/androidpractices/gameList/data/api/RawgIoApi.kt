package com.example.androidpractices.gameList.data.api

import com.example.androidpractices.gameList.data.model.GameFullResponse
import com.example.androidpractices.gameList.data.model.GamesSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RawgIoApi {
    @GET("games")
    suspend fun getGames(
        @Query("search") search: String,
        @Query("page") page: Int = 1
    ): GamesSearchResponse

    @GET("games/{id}")
    suspend fun getGame(
        @Path("id") id: String,
    ): GameFullResponse
}
