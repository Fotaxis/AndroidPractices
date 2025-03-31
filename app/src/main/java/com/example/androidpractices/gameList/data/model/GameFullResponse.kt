package com.example.androidpractices.gameList.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class GameFullResponse(
    val id: String?,
    @SerializedName("name")
    val title: String?,
    val description: String?,
    val tba: Boolean?,
    val released: String?,
    @SerializedName("metacritic_platforms")
    val ratings: List<RatingsResponse>?,
    @SerializedName("background_image")
    val image: String?,
    val platforms: List<PlatformResponse>?,
    val metacritic: Int?,
    val developers: List<FlatNameResponse>?,
    val publishers: List<FlatNameResponse>?,
    val genres: List<FlatNameResponse>?,
    val tags: List<FlatNameResponse>?
)

class RatingsResponse(
    val metascore: Int?,
    val platform: FlatNameResponse?
)

class FlatNameResponse(
    val name: String?
)

class PlatformResponse(
    val platform: FlatNameResponse
)
