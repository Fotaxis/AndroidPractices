package com.example.androidpractices.gameList.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.androidpractices.ui.components.EmptyDataBox
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
    override val screenKey: ScreenKey = generateScreenKey(),
    val gameId: Int
) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current
        val viewModel = koinViewModel<DetailsViewModel> { parametersOf(navigation, gameId) }
        val state = viewModel.viewState

        MovieScreenContent(
            state,
            onBackPressed = { viewModel.back() },
            onRatingChanged = { viewModel.onRatingChanged(it) }
        )
    }
}

@Composable
private fun MovieScreenContent(
    state: GameDetailState,
    onBackPressed: () -> Unit,
    onRatingChanged: (Float) -> Unit
) {
    Scaffold(
        topBar = { SimpleAppBar(state.game?.title.orEmpty(), onBackPressed) },
    ) {
        val game = state.game ?: run {
            EmptyDataBox(stringResource(R.string.not_found))
            return@Scaffold
        }
        Column(
            Modifier
                .padding(it)
                .padding(Spacing.medium)
                .verticalScroll(rememberScrollState())
        ) {
            Row {
                AsyncImage(model = game.image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp),
                    contentScale = ContentScale.Crop)

                Spacer(modifier = Modifier.width(Spacing.medium))

                Column {
                    Text(
                        text = game.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = game.platforms.joinToString(", ") { it.name },
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(Spacing.medium))
                    Text(
                        text = "Metacritic: " + game.metacritic.toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

            }

            Spacer(modifier = Modifier.height(Spacing.medium))

            Text(
                text = "Разработчики: " + game.developers.joinToString(", "),
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(Spacing.small))

            Text(
                text = "Издатель: " + game.publisher,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(Spacing.small))

            Text(
                text = "Дата выхода: " + if (!game.tba) game.year else stringResource(R.string.not_announced),
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(Spacing.small))

            Text(
                text = "Жанры: " + game.genres.joinToString(", ") { it.name },
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(Spacing.small))


            Text(
                text = "Теги: " + game.tags.joinToString(", "),
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(Spacing.small))


            Text(
                text = "Описание: " + game.description,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(Spacing.small))

            Row {
                game.rating.forEach { rating ->
                    RatingItem(rating)
                }
            }

            Spacer(modifier = Modifier.height(Spacing.medium))

            RatingBar(
                rating = state.rating,
                onRatingChanged = { onRatingChanged.invoke(it) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(Spacing.small))

            if (state.isRatingVisible) {
                Text(
                    text = "Ваша оценка ${state.rating}/10",
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
