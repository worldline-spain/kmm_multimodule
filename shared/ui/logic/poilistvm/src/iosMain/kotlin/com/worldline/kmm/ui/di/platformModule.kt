package com.worldline.kmm.ui.di

import com.worldline.kmm.ui.logic.poilistvm.executor.Executor
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { Executor() }
}