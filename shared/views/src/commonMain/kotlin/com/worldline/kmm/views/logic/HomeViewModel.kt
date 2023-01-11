package com.worldline.kmm.views.logic

import com.worldline.kmm.viewmodel.RootViewModel
import com.worldline.kmm.viewmodel.ViewState
import org.koin.core.component.KoinComponent

class HomeViewModel : RootViewModel<HomeState, HomeEvent, HomeAction>(HomeState.List),
    KoinComponent {

    override fun attach(): HomeViewModel = apply {
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

sealed class HomeAction {

}