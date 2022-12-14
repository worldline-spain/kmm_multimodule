package com.worldline.kmm.viewmodel


/**
 * This is a dummy class, it just extends from Android's ViewModel in the Android actual class.
 * This is necessary to inject the view model in Android, so it follows a right lifecycle.
 */
expect open class PlatformViewModel() {
    open fun detach()
}