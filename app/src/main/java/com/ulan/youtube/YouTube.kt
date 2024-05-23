package com.ulan.youtube

import android.app.Application
import com.ulan.youtube.di.networkModule
import com.ulan.youtube.di.repoModule
import com.ulan.youtube.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class YouTube : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@YouTube)
            modules(networkModule, repoModule, uiModule)
        }
    }
}