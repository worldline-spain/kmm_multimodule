package com.worldline.kmm.home.logic

import com.worldline.kmm.viewmodel.RootViewModel
import com.worldline.kmm.viewmodel.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : RootViewModel<HomeState>() {

    private val _uiState = MutableStateFlow<HomeState>(HomeState.List)

    override val state: StateFlow<HomeState> = _uiState

    override fun attach() {
        // Do nothing
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.List -> _uiState.value = HomeState.List
            HomeEvent.Map -> _uiState.value = HomeState.Map
            HomeEvent.Attach -> attach()
        }
    }
}

sealed class HomeState : ViewState() {
    object List : HomeState()
    object Map : HomeState()
}

sealed class HomeEvent {
    object Attach : HomeEvent()
    object List : HomeEvent()
    object Map : HomeEvent()
}