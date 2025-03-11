package com.example.androidpractices.ui.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.example.androidpractices.container.presentation.AppTabs

@Composable
fun MenuBar(
    selectedTab: Int,
    onTabClick: (Int) -> Unit,
) {
    BottomAppBar {
        for ((pos, tab) in AppTabs.entries.withIndex()) {
            IconButton(
                onClick = { onTabClick(pos) },
                modifier = Modifier.weight(1f),
            ) {
                Icon(
                    painter = rememberVectorPainter(tab.icon),
                    contentDescription = null,
                    tint = if (pos == selectedTab) Color.Black else Color.Gray
                )
            }
        }
    }
}