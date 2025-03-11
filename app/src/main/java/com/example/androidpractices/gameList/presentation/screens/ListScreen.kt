package com.example.androidpractices.gameList.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.androidpractices.R
import com.example.androidpractices.gameList.presentation.viewModel.ListViewModel
import com.example.androidpractices.ui.components.EmptyDataBox
import com.example.androidpractices.ui.components.GameItem
import com.example.androidpractices.ui.components.SearchBar
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Parcelize
class ListScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current
        val viewModel = koinViewModel<ListViewModel> { parametersOf(navigation) }
        val state = viewModel.viewState

        Scaffold(
            topBar = {
                SearchBar(state.query) { query -> viewModel.onQueryChanged(query) }
            }
        ) { paddingValues ->
            if (state.items.isEmpty()) {
                EmptyDataBox(stringResource( R.string.not_found))
            }
            LazyColumn(Modifier.padding(paddingValues)) {
                items(state.items) {
                    GameItem(game = it) { viewModel.onItemClicked(it.id) }
                }
            }
        }
    }
}