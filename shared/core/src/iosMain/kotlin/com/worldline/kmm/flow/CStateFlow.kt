package com.worldline.kmm.flow

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

actual open class CStateFlow<T> actual constructor(
    private val flow: StateFlow<T>
) : CFlow<T>(flow), StateFlow<T> {
    override val replayCache: List<T> get() = flow.replayCache

    override suspend fun collect(collector: FlowCollector<T>): Nothing = flow.collect(collector)

    override val value: T get() = flow.value
}