package com.example.androidpractices.gameList.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidpractices.gameList.data.dao.GameDao
import com.example.androidpractices.gameList.data.entity.GameDbEntity

@Database(entities = [GameDbEntity::class], version = 1)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}