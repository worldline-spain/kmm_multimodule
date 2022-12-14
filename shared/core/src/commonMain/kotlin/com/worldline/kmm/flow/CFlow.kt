package com.worldline.kmm.flow

import kotlinx.coroutines.flow.Flow

/**
 * Wrapper class for coroutines Flow, so iOS keeps the types
 */
expect class CFlow<T>(flow: Flow<T>) : Flow<T>

fun <T> Flow<T>.cFlow(): CFlow<T> = CFlow(this)