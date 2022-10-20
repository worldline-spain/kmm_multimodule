package com.worldline.kmm.local.di

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.worldline.kmm.db.kmm
import org.koin.core.module.Module

actual fun Module.localPlatformModule() {
    single<SqlDriver> {
        NativeSqliteDriver(
            kmm.Schema,
            "db"
        )
    }
}