package com.example.androidpractices.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.androidpractices.gameList.data.repository.GamesRepository
import com.example.androidpractices.gameList.domain.entity.GameShortEntity
import com.example.androidpractices.ui.theme.Spacing

@Composable
fun GameItem(
    game: GameShortEntity,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(Spacing.medium)
            .fillMaxWidth()
            .clickable { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = game.image,
            contentDescription = game.title,
            modifier = Modifier
                .size(36.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.width(Spacing.medium))

        Column {
            Text(
                text = game.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = game.platforms.joinToString(", ") { it.name },
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameItemPreview() {
    GameItem(GamesRepository().getList("").first())
}