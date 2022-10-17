package com.worldline.kmm.di

import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    commonPlatformModule()
}

expect fun Module.commonPlatformModule()