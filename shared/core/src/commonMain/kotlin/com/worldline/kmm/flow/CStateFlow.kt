package com.worldline.kmm.flow

import kotlinx.coroutines.flow.StateFlow

/**
 * Wrapper class for coroutines StateFlow, so iOS keeps the types
 */
expect open class CStateFlow<T>(flow: StateFlow<T>) : StateFlow<T>

fun <T> StateFlow<T>.cStateFlow(): CStateFlow<T> = CStateFlow(this)