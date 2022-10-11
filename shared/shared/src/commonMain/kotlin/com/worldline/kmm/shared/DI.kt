package com.worldline.kmm.shared.shared

import com.worldline.kmm.di.commonModule
import com.worldline.kmm.local.di.localModule
import com.worldline.kmm.remote.di.remoteModule
import com.worldline.kmm.repository.di.repositoryModule
import com.worldline.kmm.viewModels.di.viewModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module


fun initKoin(appModule: Module): KoinApplication {
    val koinApplication = startKoin {
        modules(
            appModule,
            commonModule,
            repositoryModule,
            remoteModule,
            localModule,
            viewModule
        )
    }

    return koinApplication
}