package com.example.androidpractices.profile.presentation.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractices.profile.domain.repository.IProfileRepository
import com.example.androidpractices.profile.presentation.state.EditState
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val repository: IProfileRepository
) : ViewModel() {
    private val mutableState = MutableEditState()
    val viewState = mutableState as EditState

    init {
        viewModelScope.launch {
            repository.getProfile()?.let {
                mutableState.profilePictureURI = it.profilePictureUri.toUri()
                mutableState.name = it.name
                mutableState.documentURL = it.documentUrl
                mutableState.profession = it.profession
            }
        }
        mutableState.isShowPermission = true
    }

    fun onDoneClicked() {
        viewModelScope.launch {
            repository.setProfile(
                viewState.profilePictureURI.toString(),
                viewState.name,
                viewState.documentURL,
                viewState.profession
            )
        }
    }

    fun onProfilePictureClicked() {
        mutableState.isShowSelectionDialog = true
    }

    fun onProfilePictureCanceled() {
        mutableState.isShowSelectionDialog = false
    }

    fun onProfilePictureSelected(uri: Uri?) {
        uri?.let { mutableState.profilePictureURI = it }
    }

    fun onPermissionClosed() {
        mutableState.isShowPermission = false
    }

    fun onNameChanged(name: String) {
        mutableState.name = name
    }

    fun onDocumentChanged(url: String) {
        mutableState.documentURL = url
    }

    fun onProfessionChanged(profession: String) {
        mutableState.profession = profession
    }

    private class MutableEditState : EditState {
        override var profilePictureURI: Uri by mutableStateOf(Uri.EMPTY)
        override var name by mutableStateOf("")
        override var profession by mutableStateOf("")
        override var documentURL by mutableStateOf("")
        override var isShowPermission by mutableStateOf(false)
        override var isShowSelectionDialog by mutableStateOf(false)
    }
}