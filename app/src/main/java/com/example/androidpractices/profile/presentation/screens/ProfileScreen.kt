package com.example.androidpractices.profile.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androidpractices.R
import com.example.androidpractices.profile.presentation.viewModel.ProfileViewModel
import com.example.androidpractices.ui.components.ProfileItem
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.forward
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Parcelize
class ProfileScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current
        val viewModel = koinViewModel<ProfileViewModel> { parametersOf(navigation) }
        val state = viewModel.viewState

        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(R.string.profile))
                    },
                    actions = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clickable { navigation.forward(EditProfileScreen()) }
                        )
                    }
                )
            }
        )
        { paddingValues ->
            ProfileItem(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                profilePictureURI = state.profilePictureURI,
                name = state.name,
                profession = state.profession,
                documentURL = state.documentURL,
                onDocumentClicked = {
                    viewModel.onDocumentClick()
                }
            )
        }
    }
}


