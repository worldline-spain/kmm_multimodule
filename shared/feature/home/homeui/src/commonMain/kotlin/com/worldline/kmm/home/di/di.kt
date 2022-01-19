package com.worldline.kmm.home.di

import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module

object HomeUiModule {
    fun init() = init
    fun initiOS() = startKoin { init }
}

private val init by lazy {
    loadKoinModules(platformModule)
}

expect val platformModule: Module