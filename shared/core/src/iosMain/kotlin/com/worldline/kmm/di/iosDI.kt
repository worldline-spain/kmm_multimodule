package com.worldline.kmm.di

import com.worldline.kmm.viewmodel.executor.Executor
import org.koin.core.module.Module

actual fun Module.commonPlatformModule() {
    single { Executor() }
}