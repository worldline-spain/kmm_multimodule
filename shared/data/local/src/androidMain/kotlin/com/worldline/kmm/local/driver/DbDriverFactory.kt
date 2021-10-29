package com.worldline.kmm.local.driver

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.worldline.kmm.db.kmm

actual class DbDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(
        schema = kmm.Schema,
        context = context,
        name = "db"
    )
}