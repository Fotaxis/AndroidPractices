package com.example.androidpractices.profile.presentation.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.androidpractices.R
import com.example.androidpractices.profile.presentation.viewModel.EditProfileViewModel
import com.example.androidpractices.ui.components.PictureSelectionDialog
import com.example.androidpractices.ui.components.ProfileEditForm
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.back
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.io.File
import java.util.Date

@Parcelize
class EditProfileScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("ObsoleteSdkInt")
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current
        val viewModel = koinViewModel<EditProfileViewModel> { parametersOf(navigation) }
        val state = viewModel.viewState
        val context = LocalContext.current

        var imageUri by remember { mutableStateOf<Uri?>(null) }
        val pickMedia: ActivityResultLauncher<PickVisualMediaRequest> =
            rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                viewModel.onProfilePictureSelected(uri)
            }
        val mGetContent = rememberLauncherForActivityResult(
            ActivityResultContracts.TakePicture()
        ) { success: Boolean ->
            if (success) {
                viewModel.onProfilePictureSelected(imageUri)
            }
        }
        val requestPermissionLauncher =
            rememberLauncherForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { map: Map<String, Boolean> ->
                if (map.values.contains(false)) {
                    viewModel.onPermissionRequestDenied()
                }
                viewModel.onPermissionClosed()
            }

        if (state.isShowPermissionDeniedDialog) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.onDismissPermissionDialog()
                    navigation.back()
                },
                text = { Text(stringResource(R.string.permission_denied)) },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.onDismissPermissionDialog()
                            navigation.back()
                        }
                    ) {
                        Text(stringResource(R.string.ok))
                    }
                }
            )
        }


        fun onCameraSelected() {
            val baseDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            )
            val pictureFile = File(baseDir, "picture_${Date().time}.jpg")
            imageUri = FileProvider.getUriForFile(
                context,
                context.packageName + ".provider",
                pictureFile,
            )
            imageUri?.let { mGetContent.launch(it) }
        }

        if (state.isShowSelectionDialog) {
            PictureSelectionDialog(
                onDialogDismiss = { viewModel.onProfilePictureCanceled() },
                onGallerySelected = {
                    pickMedia.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                },
                onCameraSelected = {
                    onCameraSelected()
                    viewModel.onProfilePictureCanceled()
                }
            )
        }


        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(R.string.profile_edit))
                    },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            Modifier
                                .padding(end = 8.dp)
                                .clickable {
                                    navigation.back()
                                }
                        )
                    },
                    actions = {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clickable {
                                    viewModel.onDoneClicked()
                                    navigation.back()
                                }
                        )
                    }
                )
            }
        ) { paddingValues ->
            ProfileEditForm(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize(),
                profilePictureURI = state.profilePictureURI,
                onProfilePictureClicked = { viewModel.onProfilePictureClicked() },
                name = state.name,
                onNameChanged = { name -> viewModel.onNameChanged(name) },
                documentURL = state.documentURL,
                onDocumentChanged = { url -> viewModel.onDocumentChanged(url) },
                profession = state.profession,
                onProfessionChanged = { profession -> viewModel.onProfessionChanged(profession) },
                time = state.notificationTime,
                timeString = state.timeString,
                onTimeChanged = { time -> viewModel.onTimeChanged(time) },
                timeError = state.timeError,
                showTimePicker = state.isShowTimePicker,
                onTimePickerClicked = { viewModel.onTimeInputClicked() },
                onTimeCanceled = { viewModel.onTimeCanceled() },
                onTimeConfirmed = { h, m -> viewModel.onTimeConfirmed(h, m) }
            )
        }

        if (state.isShowPermission) {
            LaunchedEffect(Unit) {
                val permissions = mutableListOf<String>()
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q &&
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
                if (
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    permissions.add(Manifest.permission.POST_NOTIFICATIONS)
                }

                requestPermissionLauncher.launch(permissions.toTypedArray())
            }
        }
    }
}

