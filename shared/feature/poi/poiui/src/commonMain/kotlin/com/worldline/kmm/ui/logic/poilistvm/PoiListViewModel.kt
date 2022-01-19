package com.worldline.kmm.ui.logic.poilistvm

import com.worldline.kmm.core.Poi
import com.worldline.kmm.feature.poi.PoiRepository
import com.worldline.kmm.viewmodel.RootViewModel
import com.worldline.kmm.viewmodel.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PoiListViewModel : RootViewModel<PoiListState>(), KoinComponent {

    private val poiRepository: PoiRepository by inject()

    private val _uiState = MutableStateFlow<PoiListState>(PoiListState.InProgress)

    override val state: StateFlow<PoiListState> = _uiState

    fun onEvent(event: PoiListEvent) = when (event) {
        PoiListEvent.Attach -> attach()
        is PoiListEvent.OnItemClick -> TODO("todo")
    }

    override fun attach() {
        vmScope.launch {
            _uiState.value = PoiListState.InProgress

            execute { poiRepository.getAll(false) }.fold(
                error = { _uiState.value = PoiListState.Error(it) },
                success = { _uiState.value = PoiListState.Success(it) }
            )
        }
    }
}

sealed class PoiListState : ViewState() {
    object InProgress : PoiListState()
    class Error(val error: com.worldline.kmm.core.Error) : PoiListState()
    class Success(val pois: List<Poi>) : PoiListState()
}

sealed class PoiListEvent {
    object Attach : PoiListEvent()
    data class OnItemClick(val poi: Poi) : PoiListEvent()
}