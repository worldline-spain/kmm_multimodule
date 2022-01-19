package com.worldline.kmm.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NavigationViewModel : RootViewModel<NavigationState>() {

    private val _uiState = MutableStateFlow<NavigationState>(NavigationState.Home.List)

    override val state: StateFlow<NavigationState> = _uiState

    override fun attach() {
        // do nothing
    }

    fun onEvent(event: NavigationEvent) = when (event) {
        NavigationEvent.Home.OnListTap -> _uiState.value = NavigationState.Home.List
        NavigationEvent.Home.OnMapTap -> _uiState.value = NavigationState.Home.Map
        is NavigationEvent.OnDetailTap -> _uiState.value = NavigationState.Detail(event.id)
    }
}

sealed class NavigationState : ViewState() {
    sealed class Home : NavigationState() {
        object List : Home()
        object Map : Home()
    }

    data class Detail(val id: String) : NavigationState()
}

sealed class NavigationEvent {
    sealed class Home : NavigationEvent() {
        object OnListTap : Home()
        object OnMapTap : Home()
    }

    data class OnDetailTap(val id: String) : NavigationEvent()
}