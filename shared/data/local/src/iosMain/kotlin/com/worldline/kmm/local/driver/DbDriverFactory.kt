package com.worldline.kmm.local.driver

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.worldline.kmm.db.kmm

actual class DbDriverFactory {
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(kmm.Schema, "db")
}