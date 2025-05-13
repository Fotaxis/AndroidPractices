package com.example.androidpractices.profile.presentation.viewModel

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractices.R
import com.example.androidpractices.profile.domain.repository.IProfileRepository
import com.example.androidpractices.profile.presentation.receiver.NotificationReceiver
import com.example.androidpractices.profile.presentation.state.EditState
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class EditProfileViewModel(
    private val repository: IProfileRepository
) : ViewModel() {
    private val mutableState = MutableEditState()
    val viewState = mutableState as EditState
    private val context: Context by KoinJavaComponent.inject(Context::class.java)
    private val formatter = DateTimeFormatter.ofPattern("HH:mm")

    init {
        viewModelScope.launch {
            repository.getProfile()?.let {
                mutableState.profilePictureURI = it.profilePictureUri.toUri()
                mutableState.name = it.name
                mutableState.documentURL = it.documentUrl
                mutableState.profession = it.profession
                tryParse(it.notificationTime)?.let { time ->
                    mutableState.notificationTime = time
                    updateTimeString()
                }
            }
        }
        mutableState.isShowPermission = true
    }

    fun onDoneClicked() {
        validateTime()
        if (mutableState.timeError != null) return
        viewModelScope.launch {
            repository.setProfile(
                viewState.profilePictureURI.toString(),
                viewState.name,
                viewState.documentURL,
                viewState.profession,
                viewState.notificationTime
            )
            saveNotification()
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

    fun onTimeInputClicked() {
        mutableState.isShowTimePicker = true
    }

    fun onTimeChanged(time: String) {
        mutableState.timeString = time
        validateTime()
    }

    fun onTimeConfirmed(h: Int, m: Int) {
        mutableState.notificationTime = LocalTime.of(h, m)
        mutableState.timeError = null
        updateTimeString()
        onTimeCanceled()
    }

    fun onTimeCanceled() {
        mutableState.isShowTimePicker = false
    }

    private fun saveNotification() {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val dateTime = LocalDateTime.of(LocalDate.now(), viewState.notificationTime)
        val timeInMillis = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        val notifyIntent = Intent(context, NotificationReceiver::class.java)
        notifyIntent.putExtras(
            Bundle().apply {
                putString(
                    "NOTIFICATION",
                    context.getString(R.string.notifications_content, viewState.name)
                )
            }
        )

        val notifyPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            notifyIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        try {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                notifyPendingIntent
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    fun onPermissionRequestDenied() {
        mutableState.isShowPermissionDeniedDialog = true
    }

    fun onDismissPermissionDialog() {
        mutableState.isShowPermissionDeniedDialog = false
    }

    private fun validateTime() {
        try {
            mutableState.notificationTime = LocalTime.parse(mutableState.timeString, formatter)
            mutableState.timeError = null
        } catch (e: Exception) {
            mutableState.timeError = context.getString(R.string.notifications_invalid_time)
        }
    }

    private fun updateTimeString() {
        mutableState.timeString = formatter.format(viewState.notificationTime)
    }

    private fun tryParse(date: String): LocalTime? {
        return try {
            LocalTime.parse(date)
        } catch (e: Exception) {
            null
        }
    }

    private class MutableEditState : EditState {
        override var profilePictureURI: Uri by mutableStateOf(Uri.EMPTY)
        override var name by mutableStateOf("")
        override var profession by mutableStateOf("")
        override var documentURL by mutableStateOf("")
        override var isShowPermission by mutableStateOf(false)
        override var isShowPermissionDeniedDialog by mutableStateOf(false)
        override var isShowSelectionDialog by mutableStateOf(false)
        override var notificationTime: LocalTime by mutableStateOf(LocalTime.now())
        override var timeString: String by mutableStateOf("")
        override var timeError: String? by mutableStateOf(null)
        override var isShowTimePicker: Boolean by mutableStateOf(false)
    }
}