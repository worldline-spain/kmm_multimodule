package com.worldline.kmm.local.di

import com.worldline.kmm.local.PoiLocal
import com.worldline.kmm.local.SharedMemoryPoiLocal
import org.koin.core.module.Module
import org.koin.dsl.module

val localModule = module {
    single<PoiLocal> {
        SharedMemoryPoiLocal()
    }
}

expect val platformModule:Module