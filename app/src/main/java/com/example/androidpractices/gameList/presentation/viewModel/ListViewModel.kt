package com.example.androidpractices.gameList.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractices.core.coroutinesUtils.launchLoadingAndError
import com.example.androidpractices.gameList.domain.entity.GameShortEntity
import com.example.androidpractices.gameList.domain.entity.Platform
import com.example.androidpractices.gameList.domain.repository.IGamesRepository
import com.example.androidpractices.gameList.presentation.screens.GameScreen
import com.example.androidpractices.gameList.presentation.state.GamesListState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.debounce
import org.koin.java.KoinJavaComponent.inject
import java.time.Duration

class ListViewModel(
    private val repository: IGamesRepository,
    private val navigation: StackNavContainer
) : ViewModel() {
    private val mutableState = MutableGamesListState()
    private val textChangesFlow = MutableStateFlow("")
    private var filterTypes: Set<Platform> = emptySet()

    private val dataStore: DataStore<Preferences> by inject(DataStore::class.java)
    private val typesKey = stringSetPreferencesKey(GAME_PLATFORMS_TYPES)

    val viewState = mutableState as GamesListState

    init {
        mutableState.typesVariants = setOf(
            Platform("PC", 4),
            Platform("PlayStation 5", 187),
            Platform("Xbox Series S/X", 186)
        )
        viewModelScope.launch {
            textChangesFlow
                .debounce(Duration.ofSeconds(1L))
                .collect { loadGames(it) }
        }
        viewModelScope.launch {
            dataStore.data.collect { data ->
                filterTypes = data[typesKey]
                    ?.mapNotNull { platformId ->
                        mutableState.typesVariants.firstOrNull { it.id == platformId.toInt() }
                    }
                    ?.toSet()
                    ?: emptySet()
                updateBadge()
            }
        }
    }

    private fun loadGames(query: String) {
        mutableState.items = emptyList()
        mutableState.isEmpty = true
        mutableState.error = null

        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }

        ) {
            mutableState.items = repository.getList(query, filterTypes)
            mutableState.isEmpty = mutableState.items.isEmpty()
        }
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        viewModelScope.launch { textChangesFlow.emit(query) }
    }

    fun onItemClicked(id: String) {
        navigation.forward(GameScreen(gameId = id))
    }

    fun onItemLongClicked(game: GameShortEntity) {
        viewModelScope.launch {
//            if (game.isFavorite) {
//                repository.removeFavorite(game)
//                game.isFavorite = false
//            }
//            else {
//                repository.saveFavorite(game)
//                game.isFavorite = true
//            }
            repository.saveFavorite(game)
        }
    }

    fun onFiltersConfirmed() {
        if (filterTypes != mutableState.selectedTypes) {
            filterTypes = mutableState.selectedTypes
            loadGames(textChangesFlow.value)
            updateBadge()
            viewModelScope.launch {
                dataStore.edit {
                    if (filterTypes.isEmpty()) {
                        it.remove(typesKey)
                    } else {
                        it[typesKey] = filterTypes.map { it.id.toString() }.toSet()
                    }
                }
            }
        }
        onSelectionDialogDismissed()
    }

    fun onFiltersClicked() {
        mutableState.selectedTypes = filterTypes
        mutableState.showTypesDialog = true
    }

    fun onFiltersCanceled() {
        mutableState.showTypesDialog = false
    }

    fun onFiltersChanged(variant: Platform, selected: Boolean) {
        mutableState.selectedTypes = mutableState.selectedTypes.run {
            if (selected) plus(variant) else minus(variant)
        }
    }

    private fun updateBadge() {
        mutableState.hasBadge =
            filterTypes.isNotEmpty()
    }

    private fun onSelectionDialogDismissed() {
        mutableState.showTypesDialog = false
    }

    private class MutableGamesListState(
    ) : GamesListState {
        override var query by mutableStateOf(String())
        override var items: List<GameShortEntity> by mutableStateOf(emptyList())
        override var isEmpty: Boolean by mutableStateOf(true)
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
        override var selectedTypes: Set<Platform> by mutableStateOf(emptySet())
        override var showTypesDialog: Boolean by mutableStateOf(false)
        override var typesVariants: Set<Platform> by mutableStateOf(emptySet())
        override var hasBadge: Boolean by mutableStateOf(false)
    }

    companion object {
        private const val GAME_PLATFORMS_TYPES = "GAMES_TYPES"
    }
}