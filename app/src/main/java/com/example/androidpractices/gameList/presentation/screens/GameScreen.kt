package com.example.androidpractices.gameList.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.androidpractices.R
import com.example.androidpractices.gameList.domain.entity.Rating
import com.example.androidpractices.gameList.presentation.state.GameDetailState
import com.example.androidpractices.gameList.presentation.viewModel.DetailsViewModel
import com.example.androidpractices.ui.components.FullscreenLoading
import com.example.androidpractices.ui.components.FullscreenMessage
import com.example.androidpractices.ui.components.RatingBar
import com.example.androidpractices.ui.components.SimpleAppBar
import com.example.androidpractices.ui.theme.Spacing
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Parcelize
class GameScreen(
    override val screenKey: ScreenKey = generateScreenKey(), val gameId: String
) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current
        val viewModel = koinViewModel<DetailsViewModel> { parametersOf(navigation, gameId) }
        val state = viewModel.viewState

        MovieScreenContent(
            state,
            onBackPressed = { viewModel.back() },
            onRatingChanged = { viewModel.onRatingChanged(it) },
        )
    }
}

@Composable
private fun MovieScreenContent(
    state: GameDetailState,
    onBackPressed: () -> Unit,
    onRatingChanged: (Int) -> Unit,
) {
    Scaffold(
        topBar = { SimpleAppBar(state.game?.title.orEmpty(), onBackPressed) },
    ) {
        if (state.isLoading) {
            FullscreenLoading()
            return@Scaffold
        }

        state.error?.let {
            FullscreenMessage(msg = it)
            return@Scaffold
        }

        val game = state.game ?: return@Scaffold

        Column(
            Modifier
                .padding(it)
                .padding(Spacing.medium)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Spacing.small)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(Spacing.medium)) {
                AsyncImage(
                    model = game.image,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop,
                )

                Column {
                    Text(
                        text = game.title, style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = game.platforms.joinToString(", ") { it.name },
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(Spacing.medium))
                    Text(
                        text = stringResource(R.string.metacritic, game.metacritic),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

            }

            Spacer(modifier = Modifier.height(Spacing.medium))

            Text(
                text = stringResource(R.string.developers, game.developers.joinToString(", ")),
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = stringResource(R.string.publisher, game.publishers.joinToString(", ")),
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = stringResource(
                    R.string.releaseDate,
                    if (!game.tba) game.year else stringResource(R.string.not_announced)
                ), style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = stringResource(R.string.genres, game.genres.joinToString(", ")),
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = stringResource(R.string.tags, game.tags.joinToString(", ")),
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = stringResource(R.string.description, game.description),
                style = MaterialTheme.typography.bodySmall
            )

            Row {
                game.rating.forEach { rating ->
                    RatingItem(rating)
                }
            }

            Spacer(modifier = Modifier.height(Spacing.medium))

            RatingBar(
                rating = state.rating,
                onRatingChanged = { onRatingChanged.invoke(it) },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                maxRating = state.maxRating
            )


            if (state.isRatingVisible) {
                Text(
                    text = stringResource(R.string.yourRating, state.rating, state.maxRating),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
private fun RowScope.RatingItem(rating: Rating) {
    Column(Modifier.weight(1f)) {
        Text(
            text = rating.metascore.toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = rating.platform.name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
