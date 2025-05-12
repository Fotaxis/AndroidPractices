package com.example.androidpractices.gameList.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractices.core.coroutinesUtils.launchLoadingAndError
import com.example.androidpractices.gameList.domain.entity.GameShortEntity
import com.example.androidpractices.gameList.domain.repository.IGamesRepository
import com.example.androidpractices.gameList.presentation.screens.GameScreen
import com.example.androidpractices.gameList.presentation.state.GamesListState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.debounce
import java.time.Duration

class ListViewModel(
    private val repository: IGamesRepository,
    private val navigation: StackNavContainer
) : ViewModel() {
    private val mutableState = MutableGamesListState()
    private val textChangesFlow = MutableStateFlow("")

    val viewState = mutableState as GamesListState

    init {
        viewModelScope.launch {
            textChangesFlow
                .debounce(Duration.ofSeconds(1L))
                .collect { loadGames() }
        }
    }

    private fun loadGames() {
        mutableState.items = emptyList()
        mutableState.error = null

        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }

        ) {
            mutableState.items = repository.getList(viewState.query)
        }
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        viewModelScope.launch { textChangesFlow.emit(query) }
    }

    fun onItemClicked(id: String) {
        navigation.forward(GameScreen(gameId = id))
    }

    private class MutableGamesListState : GamesListState {
        override var query by mutableStateOf(String())
        override var items: List<GameShortEntity> by mutableStateOf(emptyList())
        override val isEmpty get() = items.isEmpty()
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
    }
}