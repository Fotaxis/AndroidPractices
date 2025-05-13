package com.example.androidpractices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.app.NotificationManagerCompat
import com.example.androidpractices.container.presentation.MainTabScreen
import com.example.androidpractices.profile.presentation.channelManager.NotificationChannelManager
import com.example.androidpractices.ui.theme.AndroidPracticesTheme
import com.github.terrakok.modo.Modo.rememberRootScreen
import com.github.terrakok.modo.RootScreen
import com.github.terrakok.modo.stack.DefaultStackScreen
import com.github.terrakok.modo.stack.StackNavModel

class MainActivity : ComponentActivity() {
    private val channelManager: NotificationChannelManager by lazy {
        NotificationChannelManager(NotificationManagerCompat.from(this), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        channelManager.createNotificationChannels()
        enableEdgeToEdge()
        setContent {
            val rootScreen: RootScreen<DefaultStackScreen> = rememberRootScreen {
                DefaultStackScreen(
                    StackNavModel(
                        MainTabScreen()
                    )
                )
            }
            AndroidPracticesTheme {
                Surface(color = Color.White) {
                    rootScreen.Content(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}