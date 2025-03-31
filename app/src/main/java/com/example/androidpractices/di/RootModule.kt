package com.example.androidpractices.di

import com.example.androidpractices.gameList.data.mapper.GameResponseToEntityMapper
import com.example.androidpractices.gameList.data.repository.GamesRepository
import com.example.androidpractices.gameList.domain.repository.IGamesRepository
import com.example.androidpractices.gameList.presentation.viewModel.DetailsViewModel
import com.example.androidpractices.gameList.presentation.viewModel.ListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    single<IGamesRepository> { GamesRepository(get(), get()) }

    factory { GameResponseToEntityMapper() }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { DetailsViewModel(get(), it.get(), it.get()) }
}