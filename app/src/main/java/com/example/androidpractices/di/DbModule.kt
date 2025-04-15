package com.example.androidpractices.di

import android.content.Context
import androidx.room.Room
import com.example.androidpractices.gameList.data.database.GameDatabase
import org.koin.dsl.module

val dbModule = module {
    single { DatabaseBuilder.getInstance(get()) }
}

object DatabaseBuilder {
    private var INSTANCE: GameDatabase? = null

    fun getInstance(context: Context): GameDatabase {
        if (INSTANCE == null) {
            synchronized(GameDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            GameDatabase::class.java,
            "games"
        ).build()
}