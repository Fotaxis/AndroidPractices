package com.example.androidpractices.profile.presentation.state

import android.net.Uri

interface ProfileState {
    val profilePictureURI: Uri
    val name: String
    val documentURL: String
    val profession: String
}