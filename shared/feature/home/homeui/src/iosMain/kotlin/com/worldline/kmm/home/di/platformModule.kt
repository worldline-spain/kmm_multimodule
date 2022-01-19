package com.worldline.kmm.home.di

import com.worldline.kmm.home.logic.poilistvm.executor.Executor
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { Executor() }
}