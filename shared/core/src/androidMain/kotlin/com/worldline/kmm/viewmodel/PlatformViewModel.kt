package com.worldline.kmm.viewmodel

import androidx.lifecycle.ViewModel

actual open class PlatformViewModel actual constructor() : ViewModel() {

    // Override onCleared function, so detach() is called when ViewModel is destroyed
    override fun onCleared() {
        super.onCleared()
        detach()
    }

    actual open fun detach() {
        //Nothing to do
    }
}