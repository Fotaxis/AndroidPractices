package com.example.androidpractices.gameList.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.androidpractices.gameList.data.entity.GameDbEntity

@Dao
interface GameDao {
    @Query("SELECT * FROM GameDbEntity")
    suspend fun getAll(): List<GameDbEntity>

    @Query("SELECT * FROM GameDbEntity WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): GameDbEntity

    @Insert
    suspend fun insert(driverDbEntity: GameDbEntity)

    @Delete
    suspend fun delete(driverDbEntity: GameDbEntity)
}