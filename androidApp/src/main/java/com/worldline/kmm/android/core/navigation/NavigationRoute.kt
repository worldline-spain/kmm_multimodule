package com.worldline.kmm.android.core.navigation

/**
 * Idea taken from this article: https://proandroiddev.com/safe-compose-arguments-an-improved-way-to-navigate-in-jetpack-compose-95c84722eec2
 */

sealed class NavigationRoute(val route: String) {
    object Home : NavigationRoute("home")
}