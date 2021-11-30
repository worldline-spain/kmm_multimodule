package com.worldline.kmm.local.di

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.worldline.kmm.db.kmm
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<SqlDriver> {
            NativeSqliteDriver(
                kmm.Schema,
                "db"
            )
        }
    }