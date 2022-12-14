package com.worldline.kmm.flow

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Wrapper class for coroutines MutableStateFlow, so iOS keeps the types
 */
expect class CMutableStateFlow<T>(flow: MutableStateFlow<T>) : MutableStateFlow<T>

fun <T> MutableStateFlow<T>.cMutableStateFlow(): CMutableStateFlow<T> = CMutableStateFlow(this)