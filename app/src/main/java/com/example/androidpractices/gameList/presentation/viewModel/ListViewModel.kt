package com.example.androidpractices.gameList.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androidpractices.gameList.domain.entity.GameShortEntity
import com.example.androidpractices.gameList.domain.repository.IGamesRepository
import com.example.androidpractices.gameList.presentation.screens.GameScreen
import com.example.androidpractices.gameList.presentation.state.GamesListState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward

class ListViewModel(
    private val repository: IGamesRepository,
    private val navigation: StackNavContainer
) : ViewModel() {
    private val mutableState = MutableGamesListState()
    val viewState = mutableState as GamesListState

    init {
        loadGames()
    }

    private fun loadGames() {
        mutableState.items = repository.getList(viewState.query)
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        loadGames()
    }

    fun onItemClicked(id: Int) {
        navigation.forward(GameScreen(gameId = id))
    }

    private class MutableGamesListState: GamesListState {
        override var query by mutableStateOf(String())
        override var items: List<GameShortEntity> by mutableStateOf(emptyList())
        override val isEmpty get() = items.isEmpty()
    }
}