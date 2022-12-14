package com.worldline.kmm.viewmodel

class NavigationViewModel :
    RootViewModel<NavigationState, NavigationEvent, NavigationAction>(NavigationState.Home) {

    override fun attach(): NavigationViewModel = apply {
        // do nothing
    }

    fun onEvent(screen: NavigationEvent) = when (screen) {
        NavigationEvent.Home -> _uiState.value = NavigationState.Home
        is NavigationEvent.Detail -> _uiState.value = NavigationState.Detail(screen.id)
    }
}

sealed class NavigationState : ViewState() {
    object Home : NavigationState()
    data class Detail(val id: Long) : NavigationState()
}

sealed class NavigationEvent(val route: String) {
    object Home : NavigationEvent("home")
    data class Detail(val id: Long = 0) : NavigationEvent("detail/{poiId}") {
        fun createRoute() = "detail/$id"
    }
}

sealed class NavigationAction