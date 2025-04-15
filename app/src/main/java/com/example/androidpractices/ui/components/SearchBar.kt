package com.example.androidpractices.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.androidpractices.R

@Composable
fun RowScope.SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit = {}
) {
    TextField(
        value = query,
        onValueChange = { onQueryChanged(it) },
        label = { Text(stringResource(R.string.search)) },
        modifier = Modifier.weight(1f),
        leadingIcon = { Icon(Icons.Rounded.Search, null) }
    )
}