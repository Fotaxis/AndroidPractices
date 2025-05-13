package com.example.androidpractices.ui.components

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.androidpractices.R

@Composable
fun ProfileItem(
    modifier: Modifier = Modifier,
    profilePictureURI: Uri = Uri.EMPTY,
    name: String = "",
    documentURL: String = "",
    profession: String = "",
    onDocumentClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = profilePictureURI,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(128.dp),
            error = painterResource(R.drawable.plug)
        )
        if (name.isNotBlank()) {
            Text(
                text = name,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        if (profession.isNotBlank()) {
            Text(
                text = profession,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        if (documentURL.isNotBlank()) {
            Button(onClick = { onDocumentClicked() }) {
                Text(text = stringResource(R.string.document))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileItemPreview() {
    ProfileItem(name = stringResource(R.string.name))
}

