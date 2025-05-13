package com.example.androidpractices

import android.app.Application
import com.example.androidpractices.di.dbModule
import com.example.androidpractices.di.networkModule
import com.example.androidpractices.di.rootModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(rootModule, networkModule, dbModule)
        }
    }
}