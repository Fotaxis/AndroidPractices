package com.example.androidpractices.container.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.androidpractices.favorites.presentation.screens.FavoritesScreen
import com.example.androidpractices.gameList.presentation.screens.ListScreen
import com.example.androidpractices.profile.presentation.screens.ProfileScreen
import com.example.androidpractices.ui.components.MenuBar
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.animation.SlideTransition
import com.github.terrakok.modo.multiscreen.MultiScreen
import com.github.terrakok.modo.multiscreen.MultiScreenNavModel
import com.github.terrakok.modo.multiscreen.selectScreen
import kotlinx.parcelize.Parcelize

@Parcelize
class MainTabScreen(
    private val navModel: MultiScreenNavModel = MultiScreenNavModel(
        screens = AppTabs.entries.map { it.screen }, selected = 0
    )
) : MultiScreen(navModel) {
    @Composable
    override fun Content(modifier: Modifier) {
        Scaffold(modifier = modifier,
            bottomBar = { MenuBar(navigationState.selected) { pos -> selectScreen(pos) } }) { paddingValues ->
            SelectedScreen(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) { innerModifier ->
                SlideTransition(innerModifier)
            }
        }
    }
}

enum class AppTabs(val icon: ImageVector, val screen: Screen) {
    LIST(Icons.AutoMirrored.Rounded.List, ListScreen()),
    FAVORITES(Icons.Filled.Star, FavoritesScreen()),
    HOME(Icons.Filled.Person, ProfileScreen())
}