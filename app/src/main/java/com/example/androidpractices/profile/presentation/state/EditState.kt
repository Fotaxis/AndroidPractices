package com.example.androidpractices.profile.presentation.state

import android.net.Uri

interface EditState {
    val profilePictureURI: Uri
    val name: String
    val documentURL: String
    val profession: String
    var isShowPermission: Boolean
    var isShowSelectionDialog: Boolean
}