package com.worldline.kmm.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NavigationViewModel : RootViewModel<NavigationState>() {

    private val _uiState = MutableStateFlow<NavigationState>(NavigationState.Home)

    override val state: StateFlow<NavigationState> = _uiState

    override fun attach() {
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