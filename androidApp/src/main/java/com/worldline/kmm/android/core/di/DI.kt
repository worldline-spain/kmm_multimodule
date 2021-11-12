package com.worldline.kmm.android.core.di

import com.worldline.kmm.android.core.navigation.Navigator
import org.koin.core.module.Module
import org.koin.dsl.module

fun appModules(): List<Module> {
    return listOf(appModule)
}

val appModule = module {
    single { Navigator() }
}