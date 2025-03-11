package com.example.androidpractices.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androidpractices.ui.theme.Spacing

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit = {}
) {
    TextField(
        value = query,
        onValueChange = { onQueryChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.small),
        leadingIcon = { Icon(Icons.Rounded.Search, null) }
    )
}