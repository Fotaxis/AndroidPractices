package com.example.androidpractices.ui.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
fun ProfileEditForm(
    modifier: Modifier = Modifier,
    profilePictureURI: Uri = Uri.EMPTY,
    onProfilePictureClicked: () -> Unit = {},
    name: String = "",
    onNameChanged: (String) -> Unit = {},
    documentURL: String = "",
    onDocumentChanged: (String) -> Unit = {},
    profession: String = "",
    onProfessionChanged: (String) -> Unit = {}
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
                .size(128.dp)
                .clickable { onProfilePictureClicked() },
            error = painterResource(R.drawable.plug)
        )
        TextField(
            value = name,
            onValueChange = { onNameChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            label = { Text(stringResource(R.string.name)) }
        )
        TextField(
            value = profession,
            onValueChange = { onProfessionChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            label = { Text(stringResource(R.string.profession)) }
        )
        TextField(
            value = documentURL,
            onValueChange = { onDocumentChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            label = { Text(stringResource(R.string.document_url)) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileEditFormPreview() {
    ProfileEditForm(name = stringResource(R.string.profile))
}