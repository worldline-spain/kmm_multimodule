package com.worldline.kmm.android

import android.app.Application
import com.worldline.kmm.android.core.di.appModule
import com.worldline.kmm.shared.initKoin
import org.koin.android.ext.koin.androidContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(
            appModule,
        ).apply {
            androidContext(this@App)
        }
    }
}

val <T> T.exhaustive: T
    get() = this