package com.worldline.kmm.local.di

import com.worldline.kmm.local.PoiLocal
import com.worldline.kmm.local.SharedPoiLocal
import org.koin.core.module.Module
import org.koin.dsl.module

val localModule = module {
    single<PoiLocal> {
        SharedPoiLocal(get())
    }
}

expect val platformModule: Module