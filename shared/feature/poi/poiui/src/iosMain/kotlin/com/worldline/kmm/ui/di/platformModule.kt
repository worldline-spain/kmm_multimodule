package com.worldline.kmm.ui.di

import com.worldline.kmm.viewmodel.executor.Executor
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { com.worldline.kmm.viewmodel.executor.Executor() }
}