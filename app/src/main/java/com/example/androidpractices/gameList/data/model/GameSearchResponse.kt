package com.example.androidpractices.gameList.data.model

import com.google.gson.annotations.SerializedName

class GamesSearchResponse(
    val results: List<GameShortResponse>?
)

class GameShortResponse(
    val id: String?,
    @SerializedName("name")
    val title: String?,
    @SerializedName("background_image")
    val image: String?,
    val platforms: List<PlatformResponse>?
)