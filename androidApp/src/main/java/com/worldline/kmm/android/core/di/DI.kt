package com.worldline.kmm.android.core.di

import org.koin.core.module.Module
import org.koin.dsl.module

fun appModules(): List<Module> {
    return listOf(appModule)
}

val appModule = module {
}