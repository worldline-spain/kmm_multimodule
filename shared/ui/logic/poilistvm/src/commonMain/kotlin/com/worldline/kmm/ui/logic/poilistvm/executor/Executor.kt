package com.worldline.kmm.ui.logic.poilistvm.executor

import kotlinx.coroutines.CoroutineDispatcher

expect class Executor {
    val main: CoroutineDispatcher
    val bg: CoroutineDispatcher
}