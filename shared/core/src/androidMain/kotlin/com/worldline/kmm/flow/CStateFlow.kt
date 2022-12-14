package com.worldline.kmm.flow

import kotlinx.coroutines.flow.StateFlow

actual open class CStateFlow<T> actual constructor(
    private val flow: StateFlow<T>
) : StateFlow<T> by flow