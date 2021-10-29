package com.worldline.kmm.local.driver

import com.squareup.sqldelight.db.SqlDriver

expect class DbDriverFactory {
    fun createDriver(): SqlDriver
}