package com.worldline.kmm.ui.logic.poilistvm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

expect abstract class RootViewModel() {

    abstract val state: StateFlow<ViewState>

    protected val vmScope: CoroutineScope

    abstract fun attach()

    open fun detach()
}

open class ViewState