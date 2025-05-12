package com.example.androidpractices.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.androidpractices.favorites.presentation.viewModel.FavoritesViewModel
import com.example.androidpractices.gameList.data.mapper.GameResponseToEntityMapper
import com.example.androidpractices.gameList.data.repository.GamesRepository
import com.example.androidpractices.gameList.domain.repository.IGamesRepository
import com.example.androidpractices.gameList.presentation.viewModel.DetailsViewModel
import com.example.androidpractices.gameList.presentation.viewModel.ListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    single {
        getSharedPrefs(androidApplication())
    }

    single<SharedPreferences.Editor> {
        getSharedPrefs(androidApplication()).edit()
    }

    single {
        getDataStore(androidContext())
    }

    single<IGamesRepository> { GamesRepository(get(), get(), get()) }

    factory { GameResponseToEntityMapper() }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { DetailsViewModel(get(), it.get(), it.get()) }
    viewModel { FavoritesViewModel(get()) }
}

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences("default", Context.MODE_PRIVATE)
}

fun getDataStore(androidContext: Context): DataStore<Preferences> =
    PreferenceDataStoreFactory.create {
        androidContext.preferencesDataStoreFile("default")
    }
