package com.worldline.kmm.local.di

import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        /*single<SqlDriver> {
            /*NativeSqliteDriver(
                kmm.Schema,
                "db"
            )*/
        }*/
    }