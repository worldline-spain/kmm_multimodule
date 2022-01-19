package com.worldline.kmm.home.logic

import com.worldline.kmm.viewmodel.RootViewModel
import com.worldline.kmm.viewmodel.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : RootViewModel() {

    private val _uiState = MutableStateFlow<HomeState>(HomeState.List)

    override val state: StateFlow<ViewState> = _uiState

    override fun attach() {
        // Do nothing
    }
}

sealed class HomeState : ViewState() {
    object List : HomeState()
    object Map : HomeState()
}