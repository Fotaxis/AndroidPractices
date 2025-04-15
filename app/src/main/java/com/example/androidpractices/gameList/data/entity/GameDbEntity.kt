package com.example.androidpractices.gameList.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class GameDbEntity(
    @PrimaryKey(autoGenerate = false) val id: Long? = null,
    @ColumnInfo(name = "gameTitle") val title: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "platforms") val platforms: String?
)