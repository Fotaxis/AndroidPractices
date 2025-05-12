package com.example.androidpractices.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.androidpractices.R

@Composable
fun PictureSelectionDialog(
    onDialogDismiss: () -> Unit = {},
    onGallerySelected: () -> Unit = {},
    onCameraSelected: () -> Unit = {},
) {
    Dialog(onDismissRequest = { onDialogDismiss() }) {
        Surface(
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = stringResource(R.string.image_source),
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(R.string.gallery),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            onGallerySelected()
                            onDialogDismiss()
                        })
                Text(
                    text = stringResource(R.string.camera),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            onCameraSelected()
                            onDialogDismiss()
                        }
                )
            }
        }
    }
}

@Preview
@Composable
fun ImageSourceDialogPreview() {
    PictureSelectionDialog()
}