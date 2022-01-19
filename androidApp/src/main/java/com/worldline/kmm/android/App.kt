package com.worldline.kmm.android

import android.app.Application
import com.worldline.kmm.android.core.di.appModules
import com.worldline.kmm.ui.di.PoiUiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModules())
            PoiUiModule.init()
        }
    }
}