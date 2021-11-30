package com.worldline.kmm.android.core.extension

import android.util.Log

fun Any.logi(message: String) {
    Log.i(this::class.java.simpleName, message)
}

fun Any.loge(message: String) {
    Log.e(this::class.java.simpleName, message)
}

fun Any.loge(message: String, exception: Exception) {
    Log.e(this::class.java.simpleName, message, exception)
}

fun Any.logd(message: String) {
    Log.d(this::class.java.simpleName, message)
}