package com.worldline.kmm.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NavigationViewModel : RootViewModel<NavigationState>() {

    private val _uiState = MutableStateFlow<NavigationState>(NavigationState.Home.List)

    override val state: StateFlow<NavigationState> = _uiState

    override fun attach() {
        // do nothing
    }

    fun onEvent(screen: NavigationEvent) = when (screen) {
        NavigationEvent.Home.List -> _uiState.value = NavigationState.Home.List
        NavigationEvent.Home.Map -> _uiState.value = NavigationState.Home.Map
        is NavigationEvent.Detail -> _uiState.value = NavigationState.Detail(screen.id)
    }
}

sealed class NavigationState : ViewState() {
    sealed class Home : NavigationState() {
        object List : Home()
        object Map : Home()
    }

    data class Detail(val id: Long) : NavigationState()
}

sealed class NavigationEvent(val route: String) {
    sealed class Home(route: String) : NavigationEvent(route) {
        object List : Home("home/list")
        object Map : Home("home/map")
    }

    data class Detail(val id: Long = 0) : NavigationEvent("detail/{poiId}") {
        fun createRoute() = "detail/$id"
    }
}