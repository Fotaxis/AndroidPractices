package com.example.androidpractices.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int,
    maxRating: Int = 5,
    onRatingChanged: (Int) -> Unit
) {
    Row(modifier) {
        for (i in 1..maxRating) {
            IconButton(onClick = { onRatingChanged(i) }) {
                Icon(
                    if (i <= rating) Icons.Filled.Star else Icons.TwoTone.Star,
                    null
                )
            }
        }
    }
}