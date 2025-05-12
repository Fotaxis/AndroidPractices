package com.example.androidpractices

import android.app.Application
import com.example.androidpractices.di.dbModule
import com.example.androidpractices.di.networkModule
import com.example.androidpractices.di.rootModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(rootModule, networkModule, dbModule)
        }
    }
}