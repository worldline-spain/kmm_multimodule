package com.worldline.kmm.views.di

import com.worldline.kmm.viewmodel.executor.Executor
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { Executor() }
}