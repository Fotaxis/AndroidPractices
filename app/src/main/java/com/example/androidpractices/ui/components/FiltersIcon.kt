package com.example.androidpractices.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidpractices.ui.theme.Spacing

@Composable
fun BadgedIcon(
    hasBadge: Boolean = false,
    onPressed: () -> Unit
) {
    BadgedBox(
        badge = { if (hasBadge) Badge() },
        Modifier.padding(Spacing.small)
    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "More",
            modifier = Modifier.clickable { onPressed() }
        )
    }
}

@Preview
@Composable
fun BadgedIconPreview() {
    BadgedIcon(hasBadge = true, onPressed = {})
}
