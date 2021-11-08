package com.worldline.kmm.ui.di

import com.worldline.kmm.feature.poi.di.PoiFeatureModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module

object PoiUiModule {
    fun init() = init
    fun initiOS() = startKoin { init }
}

private val init by lazy {
    loadKoinModules(platformModule)
    PoiFeatureModule.init()
}

expect val platformModule: Module