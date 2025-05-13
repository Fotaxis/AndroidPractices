package com.example.androidpractices.profile.presentation.state

import android.net.Uri
import org.threeten.bp.LocalTime

interface EditState {
    val profilePictureURI: Uri
    val name: String
    val documentURL: String
    val profession: String
    var isShowPermission: Boolean
    var isShowPermissionDeniedDialog: Boolean
    var isShowSelectionDialog: Boolean
    val notificationTime: LocalTime
    val timeError: String?
    val timeString: String
    val isShowTimePicker: Boolean
}