package com.worldline.kmm.local.di

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.worldline.kmm.db.kmm
import org.koin.core.module.Module

actual fun Module.localPlatformModule() {
    single<SqlDriver> {
        AndroidSqliteDriver(
            kmm.Schema,
            get(),
            "db"
        )
    }
}