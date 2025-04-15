package com.example.androidpractices.favorites.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.androidpractices.R
import com.example.androidpractices.favorites.presentation.viewModel.FavoritesViewModel
import com.example.androidpractices.ui.components.GameItem
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel

@Parcelize
class FavoritesScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {
        val viewModel = koinViewModel<FavoritesViewModel>()
        val state by viewModel.viewState.collectAsState()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(R.string.favorites_title))
                    }
                )
            },
            floatingActionButton = {
                Button(
                    onClick = { viewModel.onUpdateClick() }) {
                    Text(stringResource(R.string.update_favorites))
                }
            }
        ) { paddingValues ->
            LazyColumn(Modifier.padding(paddingValues)) {
                items(state.items) {
                    GameItem(game = it, onLongClick = { viewModel.onItemLongClick(it) })
                }
            }
        }
    }
}