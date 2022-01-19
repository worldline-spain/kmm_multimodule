package com.worldline.kmm.ui.logic.poilistvm

import com.worldline.kmm.viewmodel.RootViewModel
import com.worldline.kmm.viewmodel.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PoiDetailViewModel(private val poiId: Long) : RootViewModel<PoiDetailState>() {

    private val _uiState = MutableStateFlow<PoiDetailState>(PoiDetailState.InProgress)

    override val state: StateFlow<PoiDetailState> = _uiState

    override fun attach() {
        println("Poi ID: $poiId")
    }

    fun onEvent(event: PoiDetailEvent) {
        when (event) {
            PoiDetailEvent.Attach -> attach()
        }
    }
}

sealed class PoiDetailState : ViewState() {
    object InProgress : PoiDetailState()
}

sealed class PoiDetailEvent {
    object Attach : PoiDetailEvent()
}