package com.worldline.kmm.feature.poi.di

import com.worldline.kmm.feature.poi.PoiRepository
import com.worldline.kmm.feature.poi.SharedPoiRepository
import com.worldline.kmm.local.di.localModule
import com.worldline.kmm.local.di.platformModule
import com.worldline.kmm.remote.di.remoteModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

object PoiFeatureModule {
    fun init() = loadFeature
    fun initiOS() = startKoin { loadFeature }
}

val loadFeature by lazy {
    loadKoinModules(
        listOf(
            featurePoiModule,
            remoteModule,
            localModule,
            platformModule
        )
    )
}

val featurePoiModule = module {
    single<PoiRepository> {
        SharedPoiRepository(get(), get())
    }
}

