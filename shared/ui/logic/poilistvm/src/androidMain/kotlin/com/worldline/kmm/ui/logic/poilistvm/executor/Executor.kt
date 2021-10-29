package com.worldline.kmm.ui.logic.poilistvm.executor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class Executor() {
    actual val main: CoroutineDispatcher = Dispatchers.Main
    actual val bg: CoroutineDispatcher = Dispatchers.IO
}