package com.worldline.kmm.viewModels.logic

import com.worldline.kmm.core.Poi
import com.worldline.kmm.feature.repository.logic.Repository
import com.worldline.kmm.viewmodel.NavigationEvent
import com.worldline.kmm.viewmodel.RootViewModel
import com.worldline.kmm.viewmodel.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PoiListViewModel(private val onNavigationEvent: (NavigationEvent) -> Unit) :
    RootViewModel<PoiListState>(), KoinComponent {

    private val repository: Repository by inject()

    private val _uiState = MutableStateFlow<PoiListState>(PoiListState.InProgress)

    override val state: StateFlow<PoiListState> = _uiState

    fun onEvent(event: PoiListEvent) = when (event) {
        PoiListEvent.Attach -> attach()
        is PoiListEvent.OnItemClick -> onNavigationEvent(NavigationEvent.Detail(event.poi.id))
    }

    override fun attach() {
        vmScope.launch {
            _uiState.value = PoiListState.InProgress

            execute { repository.getAllPOIs(true) }.fold(
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